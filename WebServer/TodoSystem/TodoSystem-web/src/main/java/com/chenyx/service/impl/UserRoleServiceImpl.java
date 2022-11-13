package com.chenyx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.UserRole;
import com.chenyx.entity.dto.UserRoleDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.UserRoleQuery;
import com.chenyx.mapper.ExtUserRoleMapper;
import com.chenyx.service.IUserRoleService;
import com.chenyx.util.EntityUtil;
import com.chenyx.util.JsonResult;
import com.chenyx.util.ListUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author 空城
 * @since 2021-07-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<ExtUserRoleMapper, UserRole> implements IUserRoleService {


    /**
     * 分页查询
     *
     * @return JsonResult
    */
    @Override
    public JsonResult selectAll(UserRoleQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        Page<UserRole> page = query.buildPage(this);
        List<UserRoleDTO> records = EntityUtil.parentListToChildList(page.getRecords(), UserRoleDTO.class);
        if(ListUtil.isNotEmpty(records)){
            jsonResult.buildTrueNew(page.getTotal(),records);
        }else {
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
        if(removeById(id)){
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
    public JsonResult updateByKey(UserRoleDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(updateById(record)){
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
    public JsonResult add(UserRoleDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(save(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

}
