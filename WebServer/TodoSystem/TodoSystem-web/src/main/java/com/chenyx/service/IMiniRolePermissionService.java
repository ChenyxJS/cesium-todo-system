package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.MiniRolePermission;
import com.chenyx.entity.dto.MiniRolePermissionDTO;
import com.chenyx.entity.query.MiniRolePermissionQuery;
import com.chenyx.util.JsonResult;

/**
 * 服务类
 *
 * @author xu.wang
 * @since 2021-08-02
 */
public interface IMiniRolePermissionService extends IService<MiniRolePermission> {
    /**
     * 分页查询
     *
     * @return JsonResult
     */
    JsonResult selectAll(MiniRolePermissionQuery query);

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
    JsonResult updateByKey(MiniRolePermissionDTO record);

    /**
     * 插入
     *
     * @param record 插入信息
     * @return JsonResult
     */
    JsonResult add(MiniRolePermissionDTO record);

    /**
     * 插入
     *
     * @param roleId 角色id
     * @param json   权限json列表字符串
     * @return JsonResult
     */
    JsonResult setting(Long roleId,String json);
}
