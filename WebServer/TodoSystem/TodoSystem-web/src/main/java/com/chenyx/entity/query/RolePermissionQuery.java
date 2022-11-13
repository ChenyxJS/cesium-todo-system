package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.RolePermission;
import com.chenyx.entity.dto.RolePermissionDTO;
import com.chenyx.util.ListUtil;
import com.chenyx.util.StringUtil;
import lombok.Data;

import java.util.List;

/**
 *  查询类
 *
 * @author 空城
 * @since 2021-07-29
*/
@Data
public class RolePermissionQuery extends QueryParam {
    /**
     * 原类
     */
    private RolePermissionDTO param;
    private QueryWrapper<RolePermission> query;
    private UpdateWrapper<RolePermission> update;
    private String isHaveBlob;

    private List<Long> permIds;

    // likeParam

    public QueryWrapper<RolePermission> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new RolePermissionDTO());
            }


    //
        if(StringUtil.isNotEmpty(getParam().getRopeId())){
            query.eq("rope_id",getParam().getRopeId());
            update.eq("rope_id",getParam().getRopeId());
        }

    // 角色id
        if(StringUtil.isNotEmpty(getParam().getRopeRoleId())){
            query.eq("rope_role_id",getParam().getRopeRoleId());
            update.eq("rope_role_id",getParam().getRopeRoleId());
        }

    // 权限id
        if(StringUtil.isNotEmpty(getParam().getRopePermId())){
            query.eq("rope_perm_id",getParam().getRopePermId());
            update.eq("rope_perm_id",getParam().getRopePermId());
        }
        if(ListUtil.isNotEmpty(getPermIds())){
            query.in("rope_perm_id",getPermIds());
            update.in("rope_perm_id",getPermIds());
        }

    // 机构id
        if(StringUtil.isNotEmpty(getParam().getRopeOrgId())){
            query.eq("rope_org_id",getParam().getRopeOrgId());
            update.eq("rope_org_id",getParam().getRopeOrgId());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getRopeCreateTime())){
            query.eq("rope_create_time",getParam().getRopeCreateTime());
            update.eq("rope_create_time",getParam().getRopeCreateTime());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getRopeUpdateTime())){
            query.eq("rope_update_time",getParam().getRopeUpdateTime());
            update.eq("rope_update_time",getParam().getRopeUpdateTime());
        }
// 排序
        if(StringUtil.isNotEmpty(getOrderItem())){
            if(StringUtil.isEmpty(getOrderType())){
                query.orderByAsc(getOrderItem().split(","));
            }else if("asc".equals((getOrderType()))){
                query.orderByAsc(getOrderItem().split(","));
            }else if("desc".equals((getOrderType()))){
                query.orderByDesc(getOrderItem().split(","));
            }
        }
        return query;
    }

    @Override
    public QueryWrapper<RolePermission> getQueryP() {
        return query;
    }

}
