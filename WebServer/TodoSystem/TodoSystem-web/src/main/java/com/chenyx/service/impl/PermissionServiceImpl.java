package com.chenyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.Permission;
import com.chenyx.entity.Role;
import com.chenyx.entity.RolePermission;
import com.chenyx.entity.dto.PermissionDTO;
import com.chenyx.entity.dto.RolePermissionDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.PermissionQuery;
import com.chenyx.entity.query.RolePermissionQuery;
import com.chenyx.mapper.ExtPermissionMapper;
import com.chenyx.redis.RedisCache;
import com.chenyx.service.IPermissionService;
import com.chenyx.service.IRolePermissionService;
import com.chenyx.service.IRoleService;
import com.chenyx.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限表 服务实现类
 *
 * @author 空城
 * @since 2021-07-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<ExtPermissionMapper, Permission> implements IPermissionService {

    @Resource
    private IRoleService roleService;
    @Resource
    private IRolePermissionService rolePermissionService;
    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private RedisCache redisCache;

    /**
     * 分页查询权限表
     *
     * @return JsonResult
     */
    @Override
    public JsonResult selectAll(PermissionQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        if (StringUtil.isNotEmpty(query.getIsTree()) && "Y".equals(query.getIsTree())) {
            query.setLimit(0);
        }
        // 创建page类并查询，本语句已经查询完毕
        Page<Permission> page = query.buildPage(this);
        List<Permission> records = page.getRecords();
        if (ListUtil.isNotEmpty(records)) {

            if (StringUtil.isNotEmpty(query.getRoleId())) {
                // 需要查询该下机运营商是否拥有某些权限，则首先查询该运营商拥有的全部权限绑定信息
                RolePermissionQuery rolePermissionQuery = new RolePermissionQuery();
                rolePermissionQuery.setLimit(0);
                rolePermissionQuery.setParam(new RolePermissionDTO());
                rolePermissionQuery.getParam().setRopeRoleId(query.getRoleId());
                List<RolePermission> rolePermissions = rolePermissionService.list(rolePermissionQuery.buildExample());
                if (ListUtil.isNotEmpty(rolePermissions)) {
                    Map<Long, Long> havePermissions = new HashMap<>();
                    rolePermissions.forEach(v -> havePermissions.put(v.getRopePermId(), v.getRopePermId()));
                    if (havePermissions.size() > 0) {
                        records.forEach(v -> {
                            if (StringUtil.isNotEmpty(havePermissions.get(v.getPermId()))) {
                                v.setRoleHave(true);
                            }
                        });
                    }
                }
            }

            if (StringUtil.isNotEmpty(query.getIsTree()) && "Y".equals(query.getIsTree())) {
                Permission permission = new Permission();
                if (StringUtil.isNotEmpty(query.getIsAll()) && "Y".equals(query.getIsAll())) {
                    jsonResult.buildTrueNew(page.getTotal(), page.getRecords());
                } else {
                    jsonResult.buildTrueNew(1, permission.myObjectToTreeUtilForChildren(records, query.getQueryType()));
                }
            } else{
                jsonResult.buildTrueNew(page.getTotal(), page.getRecords());

            }
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

    /**
     * 根据主键删除权限表
     *
     * @param id 主键
     * @return JsonResult
     */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        // 首先删除子权限
        PermissionDTO cellLocal = EntityUtil.parentToChild(getById(id), PermissionDTO.class);
        if (cellLocal != null && cellLocal.getPermId() != null) {
            // 上级权限id不相同，要修改上级权限
            String pcode = cellLocal.getPermPcode();
            // 查询原权限树，用于查询需要修改的权限的下级所有权限
            QueryWrapper<Permission> query = new QueryWrapper<>();
            query.like("perm_pcode", pcode + cellLocal.getPermId() + "-%");
            List<Permission> permissions = list(query);
            permissions.add(cellLocal);
            remove(query);
            permissions.stream().forEach(v->{
                if(StringUtil.isNotEmpty(v.getPermControlUrl())){
                    String[] urls = v.getPermControlUrl().split(";");
                    for(String url:urls){
                        redisCache.deleteObject("permission-login-"+url);
                    }
                }
            });
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
     * 根据主键修改权限表
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @Override
    public JsonResult updateByKey(PermissionDTO record) {
        JsonResult jsonResult = new JsonResult();
        PermissionDTO cellLocal = EntityUtil.parentToChild(getById(record.getPermId()), PermissionDTO.class);
        if (cellLocal != null && cellLocal.getPermId() != null && !cellLocal.getPermPid().equals(record.getPermPid())) {
            // 上级权限id不相同，要修改上级权限
            String pcode = cellLocal.getPermPcode();
            // 则首先要将本权限的新上级权限查询出来
            Permission cell = getById(record.getPermPid());
            // 设置新的pcode
            // 首先设置本权限的新pcode
            cellLocal.setPermPcode(cell.getPermPcode() + cell.getPermId() + "-");
            record.setPermPcode(cellLocal.getPermPcode());
            // 查询原权限树，用于查询需要修改的权限的下级所有权限
            List<Permission> records = getC(cellLocal.getPermId(), pcode);
            if (records.size() > 0) {
                for (Permission v : records) {
                    v.setPermPcode(v.getPermPcode().replace(pcode, cellLocal.getPermPcode()));
                    // 挨个修改
                    updateById(v);
                }
            }
        }
        if (!updateById(record)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_UPDATE_ERROR, "修改失败");
            return jsonResult;
        }

        String isLogin = record.getPermLogin();
        if(StringUtil.isEmpty(isLogin) && cellLocal != null){
            isLogin = cellLocal.getPermLogin();
        }
        if(StringUtil.isEmpty(isLogin)){
            isLogin = "N";
        }
        if(StringUtil.isNotEmpty(record.getPermControlUrl())){
            if(cellLocal != null && StringUtil.isNotEmpty(cellLocal.getPermControlUrl())){
                String[] urls = cellLocal.getPermControlUrl().split(";");
                for(String url:urls){
                    redisCache.deleteObject("permission-login-"+url);
                }
            }
            String[] urls = record.getPermControlUrl().split(";");
            for(String url:urls){
                redisCache.setCacheObject("permission-login-"+url,isLogin);
            }
        }
        jsonResult.buildTrueNew();
        return jsonResult;
    }

    /**
     * 插入权限表
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @Override
    public JsonResult add(PermissionDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (record.getPermPid() == null || record.getPermPid() == 0) {
            // 如果是一级节点
            record.setPermPid(0L);
            record.setPermPcode("0-");
        } else {
            // 如果不是一级节点，则插入之前首先查询上一级节点信息并编辑pcode
            Permission productCategory = getById(record.getPermPid());
            if (productCategory != null
                    && StringUtil.isNotEmpty(productCategory.getPermId())
                    && StringUtil.isNotEmpty(productCategory.getPermPcode())) {
                record.setPermPcode(productCategory.getPermPcode() + productCategory.getPermId() + "-");
            } else {
                jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "，上级节点信息有误。");
                return jsonResult;
            }
        }
        record.setLeaf(null);
        if (save(record)) {
            String isLogin = record.getPermLogin();
            if(StringUtil.isEmpty(isLogin)){
                isLogin = "N";
            }
            if(StringUtil.isNotEmpty(record.getPermControlUrl())){
                String[] urls = record.getPermControlUrl().split(";");
                for(String url:urls){
                    redisCache.setCacheObject("permission-login-"+url,isLogin);
                }
            }
            jsonResult.buildTrueNew();
        }
        return jsonResult;
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
        List<Permission> result;
        JsonResult jsonResult = new JsonResult();
        // 首先查询本对象
        Permission organization = getById(id);
        if (organization == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "无此对象");
            return jsonResult;
        }
        result = getPOrC(id, organization.getPermPcode(), queryType);
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
    public List<Permission> getPOrC(Long id, String pCode, String queryType) {
        List<Permission> result = new ArrayList<>();
        if ("P".equals(queryType) || "PAndC".equals(queryType)) {
            List<Permission> p = getP(pCode);
            if (ListUtil.isNotEmpty(p))
                result.addAll(p);
        }
        if ("C".equals(queryType) || "PAndC".equals(queryType)) {
            List<Permission> c = getC(id, pCode);
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
    public List<Permission> getP(String pCode) {
        List<Long> ids = new ArrayList<>();
        String[] idsStr = pCode.split("-");
        for (String id : idsStr) {
            ids.add(Long.parseLong(id));
        }
        if (ListUtil.isEmpty(ids)) {
            return null;
        }
        QueryWrapper<Permission> query = new QueryWrapper<>();
        query.in("perm_id", ids);
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
    public List<Permission> getC(Long id, String pCode) {
        // 首先拼装查询条件
        QueryWrapper<Permission> query = new QueryWrapper<>();
        query.like("perm_pcode", pCode + id + "-%");
        return list(query);
    }

    /**
     * 根据条件获取模板（返回全部的）
     *
     * @param parameter 模板查询条件
     * @return JsonResult
     */
    @Override
    public List<Permission> getAllByParameterToList(PermissionQuery parameter) {
        List<Permission> records = list(parameter.buildExample());
        if (records.size() > 0) {
            if (StringUtil.isNotEmpty(parameter.getIsTree()) && "Y".equals(parameter.getIsTree())) {
                Permission organization = new Permission();
                organization.myObjectToTreeUtilForChildren(records, parameter.getQueryType());
            }
        }
        return records;
    }

    /**
     * 根据条件获取模板（返回全部根节点的）
     *
     * @param parameter 模板查询条件
     * @return JsonResult
     */
    @Override
    public List<Permission> getAllByParameterToRootList(PermissionQuery parameter) {
        List<Permission> records = list(parameter.buildExample());
        List<Permission> result = new ArrayList<>();
        if (records.size() > 0) {

            if (StringUtil.isNotEmpty(parameter.getIsTree()) && "Y".equals(parameter.getIsTree())) {
                Permission organization = new Permission();
                organization.myObjectToTreeUtilForChildren(records, parameter.getQueryType(), result);
            }
        }
        return result;
    }

    /**
     * 给角色配置权限
     *
     * @param ids    权限id列表（逗号隔开）
     * @param roleId 角色id
     * @return JsonResult
     */
    @Override
    @Transactional
    public JsonResult settingPermissionToRole(String ids,
                                              Long roleId) {
        JsonResult jsonResult = new JsonResult();
        if (StringUtil.isEmpty(roleId)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "请选择角色信息");
            return jsonResult;
        }
        // 验证角色信息是否是登录运营商的角色
        Role role = roleService.getById(roleId);
        if (role == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "角色信息错误");
            return jsonResult;
        }

        // 首先删除原配置
        RolePermissionQuery rolePermissionQuery = new RolePermissionQuery();
        rolePermissionQuery.setParam(new RolePermissionDTO());
        rolePermissionQuery.getParam().setRopeRoleId(roleId);
        rolePermissionService.remove(rolePermissionQuery.buildExample());
        // 然后将新的配置上
        if (StringUtil.isNotEmpty(ids)) {
            // 解析ids
            String[] idsStr = ids.split(",");
            if (idsStr.length > 0) {
                List<Long> idsLong = new ArrayList<>();
                for (String idStr : idsStr) {
                    idsLong.add(Long.parseLong(idStr));
                }
                if (ListUtil.isNotEmpty(idsLong)) {
                    List<RolePermission> rolePermissions = new ArrayList<>();
                    idsLong.forEach(v -> {
                        RolePermission rolePermission = new RolePermission();
                        rolePermissions.add(rolePermission);
                        rolePermission.setRopeRoleId(roleId);
                        rolePermission.setRopePermId(v);
                    });
                    if (ListUtil.isEmpty(rolePermissions)) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "没有这些权限，请联系上级管理员");
                        return jsonResult;
                    }
                    rolePermissionService.saveBatch(rolePermissions);
                }
            }
        }
        jsonResult.buildTrueNew();
        return jsonResult;
    }


    /**
     * 加行锁的查询
     *
     * @param permId 主键
     * @return Permission
     */
    @Override
    public Permission getByIdForUpdate(Long permId) {
        return baseMapper.getByIdForUpdate(permId);
    }

    /**
     * 检验是否拥有该权限（无权限管理的接口直接放行，存在多个权限的直接拦截）
     *
     * @param permControlUrl 权限控制地址
     * @return JsonResult
     */
    @Override
    public JsonResult verifyPermissionHave(HttpSession session, String permControlUrl) {
        JsonResult jsonResult = new JsonResult();

        String isLogin = redisCache.getCacheObject("permission-login-"+permControlUrl,String.class);
        if(StringUtil.isEmpty(isLogin)){
            // 当前地址不存在，则需要先去数据库中查询
            // 查询权限
            PermissionQuery permissionQuery = new PermissionQuery();
            permissionQuery.setLimit(0);
            permissionQuery.setParam(new PermissionDTO());
            permissionQuery.setPermControlUrlIsLike(permControlUrl);
            List<Permission> permissions = list(permissionQuery.buildExample());
            if(ListUtil.isEmpty(permissions)){
                // TODO:目前是无权限管理的接口直接放行
                jsonResult.buildTrueNew();
                return jsonResult;
            }else {
                // 更新redis
                isLogin = permissions.get(0).getPermLogin();
                redisCache.setCacheObject("permission-login-"+permControlUrl,isLogin);
            }
        }
        if ("Y".equals(isLogin)) {
            // 需登录
            // 根据登录角色和绑定运营商查询权限
            return sessionUtil.getLoginPer(permControlUrl,session);
        } else {
            // 权限不需登录，放行
            jsonResult.buildTrueNew();
            return jsonResult;
        }
    }

    /**
     * 检验是否拥有该权限（无权限管理的接口直接放行，存在多个权限的直接拦截）
     *
     * @param permControlUrl 权限控制地址
     * @return JsonResult
     */
    @Override
    public JsonResult verifyMiniPermissionHave(HttpSession session, String permControlUrl) {
        JsonResult jsonResult = new JsonResult();

        String isLogin = redisCache.getCacheObject("miniPermission-login-"+permControlUrl,String.class);
        if(StringUtil.isEmpty(isLogin)){
            // 放行
            jsonResult.buildTrueNew();
            return jsonResult;
        }
        if ("Y".equals(isLogin)) {
            // 需登录
            // 根据登录角色和绑定运营商查询权限
            return sessionUtil.getLoginMiniPer(permControlUrl,session);
        } else {
            // 权限不需登录，放行
            jsonResult.buildTrueNew();
            return jsonResult;
        }
    }
}
