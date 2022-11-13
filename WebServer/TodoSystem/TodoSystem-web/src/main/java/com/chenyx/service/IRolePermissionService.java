package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.RolePermission;
import com.chenyx.entity.dto.RolePermissionDTO;
import com.chenyx.entity.query.RolePermissionQuery;
import com.chenyx.util.JsonResult;

/**
 *  服务类
 *
 * @author 空城
 * @since 2021-07-29
*/
public interface IRolePermissionService extends IService<RolePermission> {
 /**
 * 分页查询
 *
 * @return JsonResult
 */
 JsonResult selectAll(RolePermissionQuery query);

 /**
 * 根据主键删除
 *
 * @param id 主键
 * @return JsonResult
 */
 JsonResult deleteByKey(Long id);

 /**
 * 根据主键修改
 *
 * @param record 修改信息
 * @return JsonResult
 */
 JsonResult updateByKey(RolePermissionDTO record);

 /**
 * 插入
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(RolePermissionDTO record);
}
