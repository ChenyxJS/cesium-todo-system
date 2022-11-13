package com.chenyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.MiniRolePermission;
import com.chenyx.entity.Role;
import com.chenyx.entity.dto.MiniRolePermissionDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.MiniRolePermissionQuery;
import com.chenyx.mapper.ExtMiniRolePermissionMapper;
import com.chenyx.service.IMiniRolePermissionService;
import com.chenyx.service.IRoleService;
import com.chenyx.util.EntityUtil;
import com.chenyx.util.JsonResult;
import com.chenyx.util.ListUtil;
import com.chenyx.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务实现类
 *
 * @author xu.wang
 * @since 2021-08-02
 */
@Service
public class MiniRolePermissionServiceImpl extends ServiceImpl<ExtMiniRolePermissionMapper, MiniRolePermission> implements IMiniRolePermissionService {

    @Resource
    private IRoleService roleService;

    /**
     * 分页查询
     *
     * @return JsonResult
     */
    @Override
    public JsonResult selectAll(MiniRolePermissionQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        Page<MiniRolePermission> page = query.buildPage(this);
        List<MiniRolePermissionDTO> records = EntityUtil.parentListToChildList(page.getRecords(), MiniRolePermissionDTO.class);
        if (ListUtil.isNotEmpty(records)) {
            jsonResult.buildTrueNew(page.getTotal(), records);
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
    public JsonResult updateByKey(MiniRolePermissionDTO record) {
        JsonResult jsonResult = new JsonResult();
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
    public JsonResult add(MiniRolePermissionDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (save(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    @Override
    public JsonResult setting(Long roleId, String json) {
        JsonResult jsonResult = new JsonResult();
        // 首先验证角色是否存在
        QueryWrapper<Role> roleQuery = new QueryWrapper<>();
        roleQuery.lambda().eq(Role::getRoleId,roleId);
        if (roleService.count(roleQuery) == 0) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "不存在该角色");
            return jsonResult;
        }
        // 删除全部该角色权限信息，并重新插入
        QueryWrapper<MiniRolePermission> miniRolePermissionQueryWrapper = new QueryWrapper<>();
        miniRolePermissionQueryWrapper.lambda().eq(MiniRolePermission::getMrpeRoleId, roleId);
        remove(miniRolePermissionQueryWrapper);
        if (StringUtil.isNotEmpty(json)) {
            List<MiniRolePermission> miniRolePermissions = JSON.parseArray(json, MiniRolePermission.class);
            if (ListUtil.isNotEmpty(miniRolePermissions)) {
                miniRolePermissions.forEach(v-> v.setMrpeRoleId(roleId));
                saveBatch(miniRolePermissions);

            }
        }
        jsonResult.buildTrueNew();
        return jsonResult;
    }

}
