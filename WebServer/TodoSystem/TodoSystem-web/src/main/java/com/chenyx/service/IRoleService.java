package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.Role;
import com.chenyx.entity.dto.RoleDTO;
import com.chenyx.entity.query.RoleQuery;
import com.chenyx.util.JsonResult;

import java.util.List;

/**
 * 服务类
 *
 * @author 空城
 * @since 2021-07-29
 */
public interface IRoleService extends IService<Role> {
    /**
     * 分页查询
     *
     * @return JsonResult
     */
    JsonResult selectAll(RoleQuery query);

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
    JsonResult updateByKey(RoleDTO record);

    /**
     * 插入
     *
     * @param record 插入信息
     * @return JsonResult
     */
    JsonResult add(RoleDTO record);

    /**
     * 根据角色id获取其权限（树）
     *
     * @param roleId 角色id
     * @return JsonResult
     */
    JsonResult getPermissionTreeByRoleId(Long roleId);


    /**
     * 根据id获取父节点或子节点
     *
     * @param id        id
     * @param queryType 查询类型：P查询上级，C查询下级，PAndC查询上级和下级
     * @return JsonResult
     */
    JsonResult getPOrC(Long id, String queryType);

    /**
     * 根据id和pCode获取父节点或子节点
     *
     * @param id        id
     * @param pCode     pCode
     * @param queryType 查询类型：P查询上级，C查询下级，PAndC查询上级和下级
     * @return JsonResult
     */
    List<Role> getPOrC(Long id, String pCode, String queryType);

    /**
     * 根据pCode获取父节点
     *
     * @param pCode pCode
     * @return JsonResult
     */
    List<Role> getP(String pCode);

    /**
     * 根据id和pCode获取子节点
     *
     * @param id    id
     * @param pCode pCode
     * @return JsonResult
     */
    List<Role> getC(Long id, String pCode);

    /**
     * 根据条件获取模板（返回全部的）
     *
     * @param parameter 模板查询条件
     * @return JsonResult
     */
    List<Role> getAllByParameterToList(RoleQuery role);

    /**
     * 根据条件获取模板（返回全部根节点的）
     *
     * @param parameter 模板查询条件
     * @return JsonResult
     */
    List<Role> getAllByParameterToRootList(RoleQuery role);
}
