package com.chenyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.Role;
import com.chenyx.entity.RolePermission;
import com.chenyx.entity.dto.RoleDTO;
import com.chenyx.entity.dto.RolePermissionDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.PermissionQuery;
import com.chenyx.entity.query.RolePermissionQuery;
import com.chenyx.entity.query.RoleQuery;
import com.chenyx.mapper.ExtRoleMapper;
import com.chenyx.service.IPermissionService;
import com.chenyx.service.IRolePermissionService;
import com.chenyx.service.IRoleService;
import com.chenyx.util.EntityUtil;
import com.chenyx.util.JsonResult;
import com.chenyx.util.ListUtil;
import com.chenyx.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author 空城
 * @since 2021-07-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<ExtRoleMapper, Role> implements IRoleService {

    @Resource
    private IRolePermissionService rolePermissionService;
    @Resource
    private IPermissionService permissionService;

    /**
     * 分页查询
     *
     * @return JsonResult
     */
    @Override
    public JsonResult selectAll(RoleQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        if (StringUtil.isNotEmpty(query.getIsTree()) && "Y".equals(query.getIsTree())) {
            query.setLimit(0);
        }
        // 创建page类并查询，本语句已经查询完毕
        Page<Role> page = query.buildPage(this);
//        List<RoleDTO> records = EntityUtil.parentListToChildList(page.getRecords(), RoleDTO.class);
        List<Role> records = page.getRecords();
        if (ListUtil.isNotEmpty(records)) {

            if (StringUtil.isNotEmpty(query.getIsTree()) && "Y".equals(query.getIsTree())) {
                Role role = new Role();
                if (StringUtil.isNotEmpty(query.getIsAll()) && "Y".equals(query.getIsAll())) {
                    jsonResult.buildTrueNew(page.getTotal(), page.getRecords());
                } else {
                    jsonResult.buildTrueNew(1, role.myObjectToTreeUtilForChildren(records, query.getQueryType()));
                }
            } else
                jsonResult.buildTrueNew(page.getTotal(), page.getRecords());
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return JsonResult
     */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        // 首先删除子权限
        RoleDTO cellLocal = EntityUtil.parentToChild(getById(id), RoleDTO.class);
        if (cellLocal != null && cellLocal.getRoleId() != null) {
            // 上级权限id不相同，要修改上级权限
            String pcode = cellLocal.getRolePCode();
            // 查询原权限树，用于查询需要修改的权限的下级所有权限
            QueryWrapper<Role> query = new QueryWrapper<>();
            query.like("role_p_code", pcode + cellLocal.getRoleId() + "-%");
            remove(query);
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "无此权限");
            return jsonResult;
        }
        if (removeById(id)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @Override
    public JsonResult updateByKey(RoleDTO record) {
        JsonResult jsonResult = new JsonResult();

        if(StringUtil.isNotEmpty(record.getRolePId())){
            RoleDTO cellLocal = EntityUtil.parentToChild(getById(record.getRoleId()), RoleDTO.class);
            if (cellLocal != null && cellLocal.getRoleId() != null && !cellLocal.getRolePId().equals(record.getRolePId())) {
                // 上级权限id不相同，要修改上级权限
                String pcode = cellLocal.getRolePCode();
                // 则首先要将本权限的新上级权限查询出来
                Role cell = getById(record.getRolePId());
                // 设置新的pcode
                // 首先设置本权限的新pcode
                cellLocal.setRolePCode(cell.getRolePCode() + cell.getRoleId() + "-");
                record.setRolePCode(cellLocal.getRolePCode());
                // 查询原权限树，用于查询需要修改的权限的下级所有权限
                List<Role> records = getC(cellLocal.getRoleId(), pcode);
                if (records.size() > 0) {
                    for (Role v : records) {
                        v.setRolePCode(v.getRolePCode().replace(pcode, cellLocal.getRolePCode()));
                        // 挨个修改
                        updateById(v);
                    }
                }
            }
        }

        if (updateById(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @Override
    public JsonResult add(RoleDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (record.getRolePId() == null || record.getRolePId() == 0) {
            // 如果是一级节点
            record.setRolePId(0L);
            record.setRolePCode("0-");
        } else {
            // 如果不是一级节点，则插入之前首先查询上一级节点信息并编辑pcode
            Role role = getById(record.getRolePId());
            if (role != null
                    && StringUtil.isNotEmpty(role.getRoleId())
                    && StringUtil.isNotEmpty(role.getRolePCode())) {
                record.setRolePCode(role.getRolePCode() + role.getRoleId() + "-");
            } else {
                jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "，上级节点信息有误。");
                return jsonResult;
            }
        }
        record.setLeaf(null);
        if (save(record)) {
            jsonResult.buildTrueNew();
        }
        jsonResult.setTip(record.getRoleId().toString());
        return jsonResult;
    }

    /**
     * 根据角色id获取其权限（树）
     *
     * @param roleId 角色id
     * @return JsonResult
     */
    @Override
    public JsonResult getPermissionTreeByRoleId(Long roleId) {
        JsonResult jsonResult = new JsonResult();
        // 根据运营商和角色信息，查询角色权限信息
        RolePermissionQuery rolePermissionQuery = new RolePermissionQuery();
        rolePermissionQuery.setLimit(0);
        rolePermissionQuery.setParam(new RolePermissionDTO());
        rolePermissionQuery.getParam().setRopeRoleId(roleId);
        List<RolePermission> rolePermissions = rolePermissionService.list(rolePermissionQuery.buildExample());
        if (ListUtil.isEmpty(rolePermissions)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "此角色暂时无任何权限，请联系管理员");
            return jsonResult;
        }
        // 拼装权限id列表
        List<Long> perIds = new ArrayList<>();
        rolePermissions.forEach(v -> perIds.add(v.getRopePermId()));
        if (ListUtil.isEmpty(perIds)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "此角色暂时无任何权限，请联系管理员");
            return jsonResult;
        }

        // 查询树状权限列表
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setLimit(0);
        permissionQuery.setRolePerIds(perIds);
        permissionQuery.setIsTree("Y");
        permissionQuery.setOrderItem("perm_order");
        permissionQuery.setOrderType("asc");
        return permissionService.selectAll(permissionQuery);
    }





    /**
     * 根据id获取父节点或子节点
     *
     * @param id        id
     * @param queryType 查询类型：P查询上级，C查询下级，PAndC查询上级和下级
     * @return JsonResult
     */
    @Override
    public JsonResult getPOrC(Long id, String queryType) {
        List<Role> result;
        JsonResult jsonResult = new JsonResult();
        // 首先查询本对象
        Role organization = getById(id);
        if (organization == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "无此对象");
            return jsonResult;
        }
        result = getPOrC(id, organization.getRolePCode(), queryType);
        if (ListUtil.isNotEmpty(result)) {
            jsonResult.buildTrueNew(1, result);
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "未查询到上级或下级");
        }
        return jsonResult;
    }

    /**
     * 根据id和pCode获取父节点或子节点
     *
     * @param id        id
     * @param pCode     pCode
     * @param queryType 查询类型：P查询上级，C查询下级，PAndC查询上级和下级
     * @return JsonResult
     */
    @Override
    public List<Role> getPOrC(Long id, String pCode, String queryType) {
        List<Role> result = new ArrayList<>();
        if ("P".equals(queryType) || "PAndC".equals(queryType)) {
            List<Role> p = getP(pCode);
            if (ListUtil.isNotEmpty(p))
                result.addAll(p);
        }
        if ("C".equals(queryType) || "PAndC".equals(queryType)) {
            List<Role> c = getC(id, pCode);
            if (ListUtil.isNotEmpty(c))
                result.addAll(c);

        }
        return result;
    }

    /**
     * 根据pCode获取父节点
     *
     * @param pCode pCode
     * @return JsonResult
     */
    @Override
    public List<Role> getP(String pCode) {
        List<Long> ids = new ArrayList<>();
        String[] idsStr = pCode.split("-");
        for (String id : idsStr) {
            ids.add(Long.parseLong(id));
        }
        if (ListUtil.isEmpty(ids)) {
            return null;
        }
        QueryWrapper<Role> query = new QueryWrapper<>();
        query.in("role_id", ids);
        return list(query);
    }

    /**
     * 根据id和pCode获取子节点
     *
     * @param id    id
     * @param pCode pCode
     * @return JsonResult
     */
    @Override
    public List<Role> getC(Long id, String pCode) {
        // 首先拼装查询条件
        QueryWrapper<Role> query = new QueryWrapper<>();
        query.like("role_p_code", pCode + id + "-%");
        return list(query);
    }

    /**
     * 根据条件获取模板（返回全部的）
     *
     * @param role 模板查询条件
     * @return JsonResult
     */
    @Override
    public List<Role> getAllByParameterToList(RoleQuery role) {
        List<Role> records = list(role.buildExample());
        if (records.size() > 0) {
            if (StringUtil.isNotEmpty(role.getIsTree()) && "Y".equals(role.getIsTree())) {
                Role organization = new Role();
                organization.myObjectToTreeUtilForChildren(records, role.getQueryType());
            }
        }
        return records;
    }

    /**
     * 根据条件获取模板（返回全部根节点的）
     *
     * @param role 模板查询条件
     * @return JsonResult
     */
    @Override
    public List<Role> getAllByParameterToRootList(RoleQuery role) {
        List<Role> records = list(role.buildExample());
        List<Role> result = new ArrayList<>();
        if (records.size() > 0) {

            if (StringUtil.isNotEmpty(role.getIsTree()) && "Y".equals(role.getIsTree())) {
                Role organization = new Role();
                organization.myObjectToTreeUtilForChildren(records, role.getQueryType(), result);
            }
        }
        return result;
    }

}
