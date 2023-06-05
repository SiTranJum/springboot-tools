package ${package.Service};

import ${package.Entity}.${entity};
import com.nwcs.common.service.IBaseService;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends IBaseService<${entity}> {

}
</#if>
