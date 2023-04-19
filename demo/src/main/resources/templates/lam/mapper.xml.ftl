<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="${cacheClassName}"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#list table.commonFields as field>
        ${field.columnName},
</#list>
        ${table.fieldNames}
    </sql>

</#if>

<#assign tableAlias = "com.nwcs.ioa.TableNameAlias"?new() />

    <select id="page4Entity" resultType="${package.Entity}.${entity}">
        SELECT ${tableAlias(table.name)}.*
        FROM ${table.name} ${tableAlias(table.name)}
        <#noparse>
            <where>deleted = 0
                <if test="ew.sqlSegment != null and ew.sqlSegment != ''">
                    AND ${ew.sqlSegment}
                </if>
            </where>
        </#noparse>
    </select>

    <select id="page4Result" resultType="${package.Entity?replace('.entity', '.result' )}.${entity}Result">
        SELECT ${tableAlias(table.name)}.*
        FROM ${table.name} ${tableAlias(table.name)}
        <#noparse>
        <where>deleted = 0
            <if test="ew.sqlSegment != null and ew.sqlSegment != ''">
                AND ${ew.sqlSegment}
            </if>
        </where>
        </#noparse>
    </select>

    <select id="list4Entity" resultType="${package.Entity}.${entity}">
        SELECT ${tableAlias(table.name)}.*
        FROM ${table.name} ${tableAlias(table.name)}
        <#noparse>
            <where>deleted = 0
                <if test="ew.sqlSegment != null and ew.sqlSegment != ''">
                    AND ${ew.sqlSegment}
                </if>
            </where>
        </#noparse>
    </select>

    <select id="list4Result" resultType="${package.Entity?replace('.entity', '.result' )}.${entity}Result">
        SELECT ${tableAlias(table.name)}.*
        FROM ${table.name} ${tableAlias(table.name)}
        <#noparse>
            <where>deleted = 0
                <if test="ew.sqlSegment != null and ew.sqlSegment != ''">
                    AND ${ew.sqlSegment}
                </if>
            </where>
        </#noparse>
    </select>

</mapper>
