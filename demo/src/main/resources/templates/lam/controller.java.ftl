package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.nwcs.common.controller.BaseController;
import ${package.Entity}.${entity};
import ${package.Entity?replace('.entity', '.so' )}.${entity}SO;
import ${package.Entity?replace('.entity', '.dto' )}.${entity}Dto;
import ${package.Service}.${table.serviceName};

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>


/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerName} extends BaseController<${table.serviceName}, ${entity}, ${entity}SO, ${entity}Dto>  {

    @Autowired
    private ${table.serviceName} ${table.serviceName?substring(1)?uncap_first};

    @Override
	public ${table.serviceName} getBaseService() {
		return ${table.serviceName?substring(1)?uncap_first} ;
	}
}
