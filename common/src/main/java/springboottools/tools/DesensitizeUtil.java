package springboottools.tools;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import springboottools.enums.SensitiveEnum;


/**
 * @author FANWENKUI771
 * @create 2019-06-18
 * @description
 */
@Slf4j
public class DesensitizeUtil {
    /**
     * 姓名脱敏
     * @param value
     * @return
     */
    public static String userName(String value){
        return around(value, SensitiveEnum.NAME.getBefore(),SensitiveEnum.NAME.getAfter(),SensitiveEnum.NAME.getMiddle());
    }

    /**
     * 身份证脱敏
     * @param value
     * @return
     */
    public static String idCard(String value){
        return around(value, SensitiveEnum.ID_CARD.getBefore(),SensitiveEnum.ID_CARD.getAfter(),SensitiveEnum.ID_CARD.getMiddle());
    }

    public static String desensitive(String value, SensitiveEnum type){
        return around(value,type.getBefore(),type.getAfter(),type.getMiddle());
    }

    /**
     * @param name
     * @param index 3
     * @param end 2
     * @return
     */
    public static String around(String name, int index, int end, int middle) {
        if (StrUtil.isBlank(name)) {
            return "";
        }

        if(index+end>=name.length()){
            return name;
        }

        String left= StringUtils.left(name, index);
        int starLength=middle>0?middle+end: StringUtils.length(name)-index;
        String right= StringUtils.leftPad(StringUtils.right(name, end), starLength, "*");

        return left.concat(right);
    }
}
