package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.Permission;
import com.chenyx.entity.dto.PermissionDTO;
import com.chenyx.entity.query.PermissionQuery;
import com.chenyx.util.JsonResult;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 权限表 服务类
 *
 * @author 空城
 * @since 2021-07-29
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 分页查询权限表
     *
     * @return JsonResult
     */
    JsonResult selectAll(PermissionQuery query);

    /**
     * 根据主键删除权限表
     *
     * @param id 主键
     * @return JsonResult
     */
    JsonResult deleteByKey(Long id);

    /**
     * 根据主键修改权限表
     *
     * @param record 修改信息
     * @return JsonResult
     */
    JsonResult updateByKey(PermissionDTO record);

    /**
     * 插入权限表
     *
     * @param record 插入信息
     * @return JsonResult
     */
    JsonResult add(PermissionDTO record);


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
    List<Permission> getPOrC(Long id, String pCode, String queryType);

    /**
     * 根据pCode获取父节点
     *
     * @param pCode pCode
     * @return JsonResult
     */
    List<Permission> getP(String pCode);

    /**
     * 根据id和pCode获取子节点
     *
     * @param id    id
     * @param pCode pCode
     * @return JsonResult
     */
    List<Permission> getC(Long id, String pCode);

    /**
     * 根据条件获取模板
     *
     * @param parameter 模板查询条件
     * @return JsonResult
     */
    List<Permission> getAllByParameterToList(PermissionQuery parameter);

    /**
     * 根据条件获取模板（返回全部根节点的）
     *
     * @param parameter 模板查询条件
     * @return JsonResult
     */
    List<Permission> getAllByParameterToRootList(PermissionQuery parameter);

    /**
     * 给角色配置权限
     *
     * @param ids    权限id列表（逗号隔开）
     * @param roleId 角色id
     * @return JsonResult
     */
    JsonResult settingPermissionToRole(String ids,
                                       Long roleId);

    /**
     * 加行锁的查询
     *
     * @param permId 主键
     * @return Permission
     */
    Permission getByIdForUpdate(Long permId);

    /**
     * 检验是否拥有该权限（无权限管理的接口直接放行，存在多个权限的直接拦截）
     *
     * @param permControlUrl 权限控制地址
     * @return JsonResult
     */
    JsonResult verifyPermissionHave(HttpSession session, String permControlUrl);

    /**
     * 检验是否拥有该权限（无权限管理的接口直接放行，存在多个权限的直接拦截）
     *
     * @param permControlUrl 权限控制地址
     * @return JsonResult
     */
    JsonResult verifyMiniPermissionHave(HttpSession session, String permControlUrl);
}
