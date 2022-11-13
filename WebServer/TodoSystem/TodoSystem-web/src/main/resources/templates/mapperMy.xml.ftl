<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.Ext${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

    </#if>
    <resultMap id="BaseDTOResultMap" extends="BaseResultMap" type="${package.Entity}.dto.${entity}DTO">

    </resultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
        <#list table.fields as field>
            <#if field.keyFlag><#--生成主键排在第一位-->
                <id column="${field.name}" property="${field.propertyName}"/>
            </#if>
        </#list>
        <#list table.commonFields as field><#--生成公共字段 -->
            <result column="${field.name}" property="${field.propertyName}"/>
        </#list>
        <#list table.fields as field>
            <#if !field.keyFlag><#--生成普通字段 -->
                <result column="${field.name}" property="${field.propertyName}"/>
            </#if>
        </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list table.commonFields as field>
            ${field.columnName},
        </#list>
        ${table.fieldNames}
    </sql>


    <!--批量插入-->
    <insert id="batchAdd" parameterType="java.util.List">
        insert into ${table.name}
        (
        <#list table.fields as field>
            <#if !field.keyFlag>
                ${field.name}<#if field_has_next>,</#if>
            </#if>
        </#list>
        )
        <trim suffixOverrides=",">
            <foreach collection="list" item="item">
                <trim prefix=" (" suffix=")" suffixOverrides=",">

                    <#list table.fields as field>
                        <#if !field.keyFlag>

                        </#if>
                    </#list>
                </trim>
                ,
            </foreach>
        </trim>
    </insert>

    <!--    加锁查询-->
    <select id="getByIdForUpdate" resultMap="BaseResultMap" parameterType="java.lang.Long">
        select
        <include refid="Base_Column_List"/>
        from ${table.name}
        where <#list table.fields as field>
            <#if field.keyFlag>
                ${field.name} = #${"{"}${field.propertyName}}
            </#if>
        </#list>
        for update
    </select>
</mapper>
