package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.UserOpenid;
import com.chenyx.entity.dto.UserOpenidDTO;
import com.chenyx.util.StringUtil;
import lombok.Data;

/**
 * user_openid_用户openid 查询类
 *
 * @author 苏友朋
 * @since 2021-07-30
*/
@Data
public class UserOpenidQuery extends QueryParam {
    /**
     * 原类
     */
    private UserOpenidDTO param;
    private QueryWrapper<UserOpenid> query;
    private UpdateWrapper<UserOpenid> update;
    private String isHaveBlob;

    // likeParam
    // 用户openid
    private String uopOpenidIsLike;
    // 公众平台类型
    private String uopPlatformIsLike;
    // 小程序appid
    private String uopAppidIsLike;
    // 备注
    private String uopRemarkIsLike;

    public QueryWrapper<UserOpenid> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new UserOpenidDTO());
            }


    //
        if(StringUtil.isNotEmpty(getParam().getUopId())){
            query.eq("uop_id",getParam().getUopId());
            update.eq("uop_id",getParam().getUopId());
        }

    // 用户id
        if(StringUtil.isNotEmpty(getParam().getUopUserId())){
            query.eq("uop_user_id",getParam().getUopUserId());
            update.eq("uop_user_id",getParam().getUopUserId());
        }

    // 用户openid
        if(StringUtil.isNotEmpty(getParam().getUopOpenid())){
            query.eq("uop_openid",getParam().getUopOpenid());
            update.eq("uop_openid",getParam().getUopOpenid());
        }
        if(StringUtil.isNotEmpty(getUopOpenidIsLike())){
            query.like("uop_openid",getUopOpenidIsLike());
            update.like("uop_openid",getUopOpenidIsLike());
        }

    // 公众平台类型
        if(StringUtil.isNotEmpty(getParam().getUopPlatform())){
            query.eq("uop_platform",getParam().getUopPlatform());
            update.eq("uop_platform",getParam().getUopPlatform());
        }
        if(StringUtil.isNotEmpty(getUopPlatformIsLike())){
            query.like("uop_platform",getUopPlatformIsLike());
            update.like("uop_platform",getUopPlatformIsLike());
        }

    // 小程序appid
        if(StringUtil.isNotEmpty(getParam().getUopAppid())){
            query.eq("uop_appid",getParam().getUopAppid());
            update.eq("uop_appid",getParam().getUopAppid());
        }
        if(StringUtil.isNotEmpty(getUopAppidIsLike())){
            query.like("uop_appid",getUopAppidIsLike());
            update.like("uop_appid",getUopAppidIsLike());
        }

    // 备注
        if(StringUtil.isNotEmpty(getParam().getUopRemark())){
            query.eq("uop_remark",getParam().getUopRemark());
            update.eq("uop_remark",getParam().getUopRemark());
        }
        if(StringUtil.isNotEmpty(getUopRemarkIsLike())){
            query.like("uop_remark",getUopRemarkIsLike());
            update.like("uop_remark",getUopRemarkIsLike());
        }

    // 状态，1：在线、0、离职
        if(StringUtil.isNotEmpty(getParam().getUopStatus())){
            query.eq("uop_status",getParam().getUopStatus());
            update.eq("uop_status",getParam().getUopStatus());
        }

    // 数据创建时间
        if(StringUtil.isNotEmpty(getParam().getUopDbCreateTime())){
            query.eq("uop_db_create_time",getParam().getUopDbCreateTime());
            update.eq("uop_db_create_time",getParam().getUopDbCreateTime());
        }

    // 数据最后一次修改时间
        if(StringUtil.isNotEmpty(getParam().getUopDbUpdateTime())){
            query.eq("uop_db_update_time",getParam().getUopDbUpdateTime());
            update.eq("uop_db_update_time",getParam().getUopDbUpdateTime());
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
    public QueryWrapper<UserOpenid> getQueryP() {
        return query;
    }

}
