package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.UserRole;
import com.chenyx.entity.dto.UserRoleDTO;
import com.chenyx.entity.query.UserRoleQuery;
import com.chenyx.util.JsonResult;

/**
 *  服务类
 *
 * @author 空城
 * @since 2021-07-29
*/
public interface IUserRoleService extends IService<UserRole> {
 /**
 * 分页查询
 *
 * @return JsonResult
 */
 JsonResult selectAll(UserRoleQuery query);

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
 JsonResult updateByKey(UserRoleDTO record);

 /**
 * 插入
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(UserRoleDTO record);
}
