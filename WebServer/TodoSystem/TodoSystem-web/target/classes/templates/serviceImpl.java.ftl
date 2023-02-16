package ${package.ServiceImpl};

import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.${entity};
import ${package.Mapper}.Ext${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${package.Entity}.enums.RequestEnum;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import ${package.Entity}.query.${entity}Query;
import ${cfg.parentBKG}.util.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${cfg.parentBKG}.util.ListUtil;
import java.util.List;
import com.chenyx.util.EntityUtil;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${"Ext"}${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${"Ext"}${table.mapperName}, ${entity}> implements ${table.serviceName} {


    /**
     * 分页查询${table.comment!}
     *
     * @return JsonResult
    */
    @Override
    public JsonResult selectAll(${entity}Query query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        Page<${entity}> page = query.buildPage(this);
        List<${entity}DTO> records = EntityUtil.parentListToChildList(page.getRecords(), ${entity}DTO.class);
        if(ListUtil.isNotEmpty(records)){
            jsonResult.buildTrueNew(page.getTotal(),records);
        }else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

    /**
     * 根据主键删除${table.comment!}
     *
     * @param id 主键
     * @return JsonResult
    */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        if(removeById(id)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改${table.comment!}
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @Override
    public JsonResult updateByKey(${entity}DTO record) {
        JsonResult jsonResult = new JsonResult();
        if(updateById(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入${table.comment!}
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @Override
    public JsonResult add(${entity}DTO record) {
        JsonResult jsonResult = new JsonResult();
        if(save(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

}
</#if>
