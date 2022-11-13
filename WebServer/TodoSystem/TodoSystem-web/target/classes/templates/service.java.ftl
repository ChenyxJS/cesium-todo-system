package ${package.Service};

import ${package.Entity}.dto.${entity}DTO;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${package.Entity}.query.${entity}Query;
import ${cfg.parentBKG}.util.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @since ${date}
*/
<#if kotlin>
 interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
 /**
 * 分页查询${table.comment!}
 *
 * @return JsonResult
 */
 JsonResult selectAll(${entity}Query query);

 /**
 * 根据主键删除${table.comment!}
 *
 * @param id 主键
 * @return JsonResult
 */
 JsonResult deleteByKey(Long id);

 /**
 * 根据主键修改${table.comment!}
 *
 * @param record 修改信息
 * @return JsonResult
 */
 JsonResult updateByKey(${entity}DTO record);

 /**
 * 插入${table.comment!}
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(${entity}DTO record);
}
</#if>
