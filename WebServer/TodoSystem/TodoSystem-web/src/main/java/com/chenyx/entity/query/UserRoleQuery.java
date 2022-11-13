package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.UserRole;
import com.chenyx.entity.dto.UserRoleDTO;
import com.chenyx.util.StringUtil;
import lombok.Data;

/**
 *  查询类
 *
 * @author 空城
 * @since 2021-07-29
*/
@Data
public class UserRoleQuery extends QueryParam {
    /**
     * 原类
     */
    private UserRoleDTO param;
    private QueryWrapper<UserRole> query;
    private UpdateWrapper<UserRole> update;
    private String isHaveBlob;

    // likeParam

    public QueryWrapper<UserRole> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new UserRoleDTO());
            }


    //
        if(StringUtil.isNotEmpty(getParam().getUsroId())){
            query.eq("usro_id",getParam().getUsroId());
            update.eq("usro_id",getParam().getUsroId());
        }

    // 用户id
        if(StringUtil.isNotEmpty(getParam().getUsroUserId())){
            query.eq("usro_user_id",getParam().getUsroUserId());
            update.eq("usro_user_id",getParam().getUsroUserId());
        }

    // 角色id
        if(StringUtil.isNotEmpty(getParam().getUsroRoleId())){
            query.eq("usro_role_id",getParam().getUsroRoleId());
            update.eq("usro_role_id",getParam().getUsroRoleId());
        }

    // 机构id
        if(StringUtil.isNotEmpty(getParam().getUsroOrgId())){
            query.eq("usro_org_id",getParam().getUsroOrgId());
            update.eq("usro_org_id",getParam().getUsroOrgId());
        }
    // 机构名
        if(StringUtil.isNotEmpty(getParam().getUsroOrgName())){
            query.eq("usro_org_name",getParam().getUsroOrgName());
            update.eq("usro_org_name",getParam().getUsroOrgName());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getUsroCreateTime())){
            query.eq("usro_create_time",getParam().getUsroCreateTime());
            update.eq("usro_create_time",getParam().getUsroCreateTime());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getUsroUpdateTime())){
            query.eq("usro_update_time",getParam().getUsroUpdateTime());
            update.eq("usro_update_time",getParam().getUsroUpdateTime());
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
    public QueryWrapper<UserRole> getQueryP() {
        return query;
    }

}
