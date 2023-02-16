package ${package.Entity}.query;

import ${cfg.parentBKG}.util.StringUtil;
import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.Data;

/**
 * ${table.comment!} 查询类
 *
 * @author ${author}
 * @since ${date}
*/
@Data
public class ${entity}Query extends QueryParam {
    /**
     * 原类
     */
    private ${entity}DTO param;
    private QueryWrapper<${entity}> query;
    private UpdateWrapper<${entity}> update;
    private String isHaveBlob;

    // likeParam
<#list table.fields as field>
    <#if !field.keyFlag>
<#--        <#if field.type = 'varchar(255)'>-->
        <#if field.type?contains("varchar")>
    // ${field.comment}
    private String ${field.propertyName}IsLike;
        </#if>
    </#if>
</#list>

    public QueryWrapper<${entity}> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new ${entity}DTO());
            }

<#list table.fields as field>

    // ${field.comment}
        if(StringUtil.isNotEmpty(getParam().get${field.propertyName?cap_first}())){
            query.eq("${field.name}",getParam().get${field.propertyName?cap_first}());
            update.eq("${field.name}",getParam().get${field.propertyName?cap_first}());
        }
<#--    <#if field.type = 'varchar(255)'>-->
    <#if field.type?contains("varchar")>
        if(StringUtil.isNotEmpty(get${field.propertyName?cap_first}IsLike())){
            query.like("${field.name}",get${field.propertyName?cap_first}IsLike());
            update.like("${field.name}",get${field.propertyName?cap_first}IsLike());
        }
    </#if>
</#list>
// 排序
        if(StringUtil.isNotEmpty(getOrderItem())){
            if(StringUtil.isEmpty(getOrderType())){
                query.orderByAsc(getOrderItem().split(","));
            }else if("asc".equals((getOrderType()))){
                query.orderByAsc(getOrderItem().split(","));
            }else if("desc".equals((getOrderType()))){
                query.orderByDesc(getOrderItem().split(","));
            }
        }
        return query;
    }

    @Override
    public QueryWrapper<${entity}> getQueryP() {
        return query;
    }

}