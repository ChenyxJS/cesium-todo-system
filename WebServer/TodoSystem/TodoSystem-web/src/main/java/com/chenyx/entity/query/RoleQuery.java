package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.Role;
import com.chenyx.entity.dto.RoleDTO;
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
public class RoleQuery extends QueryParam {
    /**
     * 原类
     */
    private RoleDTO param;
    private QueryWrapper<Role> query;
    private UpdateWrapper<Role> update;
    private String isHaveBlob;

    private String isTree;
    // 是返回全部权限还是只返回一级权限
    private String isAll;
    private String queryType = "C";


    private List<Long> roleIds;

    // likeParam
    // 层级关系
    private String rolePCodeIsLike;
    // 名称
    private String roleNameIsLike;
    // 角色编码
    private String roleCodeIsLike;
    // 备注
    private String roleRemarkIsLike;

    public QueryWrapper<Role> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new RoleDTO());
            }


    //
        if(StringUtil.isNotEmpty(getParam().getRoleId())){
            query.eq("role_id",getParam().getRoleId());
            update.eq("role_id",getParam().getRoleId());
        }
        if(ListUtil.isNotEmpty(getRoleIds())){
            query.in("role_id",getRoleIds());
            update.in("role_id",getRoleIds());
        }

    // 父节点id
        if(StringUtil.isNotEmpty(getParam().getRolePId())){
            query.eq("role_p_id",getParam().getRolePId());
            update.eq("role_p_id",getParam().getRolePId());
        }

    // 层级关系
        if(StringUtil.isNotEmpty(getParam().getRolePCode())){
            query.eq("role_p_code",getParam().getRolePCode());
            update.eq("role_p_code",getParam().getRolePCode());
        }
        if(StringUtil.isNotEmpty(getRolePCodeIsLike())){
            query.like("role_p_code",getRolePCodeIsLike());
            update.like("role_p_code",getRolePCodeIsLike());
        }

    // 名称
        if(StringUtil.isNotEmpty(getParam().getRoleName())){
            query.eq("role_name",getParam().getRoleName());
            update.eq("role_name",getParam().getRoleName());
        }
        if(StringUtil.isNotEmpty(getRoleNameIsLike())){
            query.like("role_name",getRoleNameIsLike());
            update.like("role_name",getRoleNameIsLike());
        }

    // 角色编码
        if(StringUtil.isNotEmpty(getParam().getRoleCode())){
            query.eq("role_code",getParam().getRoleCode());
            update.eq("role_code",getParam().getRoleCode());
        }
        if(StringUtil.isNotEmpty(getRoleCodeIsLike())){
            query.like("role_code",getRoleCodeIsLike());
            update.like("role_code",getRoleCodeIsLike());
        }

    // 类型（见枚举）
        if(StringUtil.isNotEmpty(getParam().getRoleType())){
            query.eq("role_type",getParam().getRoleType());
            update.eq("role_type",getParam().getRoleType());
        }

    // 备注
        if(StringUtil.isNotEmpty(getParam().getRoleRemark())){
            query.eq("role_remark",getParam().getRoleRemark());
            update.eq("role_remark",getParam().getRoleRemark());
        }
        if(StringUtil.isNotEmpty(getRoleRemarkIsLike())){
            query.like("role_remark",getRoleRemarkIsLike());
            update.like("role_remark",getRoleRemarkIsLike());
        }

    // 是否删除（1删除）
        if(StringUtil.isNotEmpty(getParam().getRoleIsDelete())){
            query.eq("role_is_delete",getParam().getRoleIsDelete());
            update.eq("role_is_delete",getParam().getRoleIsDelete());
        }

    // 状态（见枚举）
        if(StringUtil.isNotEmpty(getParam().getRoleStatus())){
            query.eq("role_status",getParam().getRoleStatus());
            update.eq("role_status",getParam().getRoleStatus());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getRoleCreateTime())){
            query.eq("role_create_time",getParam().getRoleCreateTime());
            update.eq("role_create_time",getParam().getRoleCreateTime());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getRoleUpdateTime())){
            query.eq("role_update_time",getParam().getRoleUpdateTime());
            update.eq("role_update_time",getParam().getRoleUpdateTime());
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
    public QueryWrapper<Role> getQueryP() {
        return query;
    }

}
