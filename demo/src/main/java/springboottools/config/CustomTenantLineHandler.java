package springboottools.config;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 多租户处理插件
 *
 * @author Virgil.Chen
 * @date 2022/12/23 13:37
 */
@Component
public class CustomTenantLineHandler implements TenantLineHandler {

    /**
     * 忽略添加租户ID的表
     */
    @Value("#{'${tenant.ignore-table-names:}'.split(',')}")
    private List<String> IGNORE_TABLE_NAMES;

    /**
     * 获取租户ID值表达式(可从cookie、token、缓存中取)
     *
     * @return
     */
    @Override
    public Expression getTenantId() {
        return new StringValue("value");
    }

    /**
     * 获取租户字段名(数据库的租户ID字段名)
     *
     * @return
     */
    @Override
    public String getTenantIdColumn() {
        return "bu_guid";
    }

    /**
     * 根据表名判断是否忽略拼接多租户条件
     *
     * @param tableName
     * @return
     */
    @Override
    public boolean ignoreTable(String tableName) {
        return IGNORE_TABLE_NAMES.contains(tableName);
    }
}
