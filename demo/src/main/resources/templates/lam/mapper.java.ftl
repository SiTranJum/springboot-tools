package ${package.Mapper};

import ${package.Entity}.${entity};
import com.nwcs.common.dao.BaseDao;
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends BaseDao<${entity}> {

}
</#if>