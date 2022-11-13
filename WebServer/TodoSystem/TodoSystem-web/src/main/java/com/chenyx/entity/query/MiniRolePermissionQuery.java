package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.MiniRolePermission;
import com.chenyx.entity.dto.MiniRolePermissionDTO;
import com.chenyx.util.StringUtil;
import lombok.Data;

/**
 *  查询类
 *
 * @author xu.wang
 * @since 2021-08-02
*/
@Data
public class MiniRolePermissionQuery extends QueryParam {
    /**
     * 原类
     */
    private MiniRolePermissionDTO param;
    private QueryWrapper<MiniRolePermission> query;
    private UpdateWrapper<MiniRolePermission> update;
    private String isHaveBlob;

    // likeParam
    // 权限编码
    private String mrpeCodeIsLike;
    // 权限名称
    private String mrpeNameIsLike;
    //
    private String mrpeControlUrlIsLike;
    //
    private String mrpeIsLoginIsLike;
    // 预留字段
    private String mrpeTypeIsLike;

    public QueryWrapper<MiniRolePermission> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new MiniRolePermissionDTO());
            }


    //
        if(StringUtil.isNotEmpty(getParam().getMrpeId())){
            query.eq("mrpe_id",getParam().getMrpeId());
            update.eq("mrpe_id",getParam().getMrpeId());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getMrpeRoleId())){
            query.eq("mrpe_role_id",getParam().getMrpeRoleId());
            update.eq("mrpe_role_id",getParam().getMrpeRoleId());
        }

    // 权限编码
        if(StringUtil.isNotEmpty(getParam().getMrpeCode())){
            query.eq("mrpe_code",getParam().getMrpeCode());
            update.eq("mrpe_code",getParam().getMrpeCode());
        }
        if(StringUtil.isNotEmpty(getMrpeCodeIsLike())){
            query.like("mrpe_code",getMrpeCodeIsLike());
            update.like("mrpe_code",getMrpeCodeIsLike());
        }

    // 权限名称
        if(StringUtil.isNotEmpty(getParam().getMrpeName())){
            query.eq("mrpe_name",getParam().getMrpeName());
            update.eq("mrpe_name",getParam().getMrpeName());
        }
        if(StringUtil.isNotEmpty(getMrpeNameIsLike())){
            query.like("mrpe_name",getMrpeNameIsLike());
            update.like("mrpe_name",getMrpeNameIsLike());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getMrpeControlUrl())){
            query.eq("mrpe_control_url",getParam().getMrpeControlUrl());
            update.eq("mrpe_control_url",getParam().getMrpeControlUrl());
        }
        if(StringUtil.isNotEmpty(getMrpeControlUrlIsLike())){
            query.like("mrpe_control_url",getMrpeControlUrlIsLike());
            update.like("mrpe_control_url",getMrpeControlUrlIsLike());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getMrpeIsLogin())){
            query.eq("mrpe_is_login",getParam().getMrpeIsLogin());
            update.eq("mrpe_is_login",getParam().getMrpeIsLogin());
        }
        if(StringUtil.isNotEmpty(getMrpeIsLoginIsLike())){
            query.like("mrpe_is_login",getMrpeIsLoginIsLike());
            update.like("mrpe_is_login",getMrpeIsLoginIsLike());
        }

    // 预留字段
        if(StringUtil.isNotEmpty(getParam().getMrpeType())){
            query.eq("mrpe_type",getParam().getMrpeType());
            update.eq("mrpe_type",getParam().getMrpeType());
        }
        if(StringUtil.isNotEmpty(getMrpeTypeIsLike())){
            query.like("mrpe_type",getMrpeTypeIsLike());
            update.like("mrpe_type",getMrpeTypeIsLike());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getMrpeStatus())){
            query.eq("mrpe_status",getParam().getMrpeStatus());
            update.eq("mrpe_status",getParam().getMrpeStatus());
        }

    //
        if(StringUtil.isNotEmpty(getParam().getMrpeDbCreateDate())){
            query.eq("mrpe_db_create_date",getParam().getMrpeDbCreateDate());
            update.eq("mrpe_db_create_date",getParam().getMrpeDbCreateDate());
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
    public QueryWrapper<MiniRolePermission> getQueryP() {
        return query;
    }

}
