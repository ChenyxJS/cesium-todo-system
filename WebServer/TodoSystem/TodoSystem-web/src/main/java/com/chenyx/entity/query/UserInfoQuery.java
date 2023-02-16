package com.chenyx.entity.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chenyx.entity.UserInfo;
import com.chenyx.entity.dto.UserInfoDTO;
import com.chenyx.util.StringUtil;
import lombok.Data;

/**
 * 用户信息 查询类
 *
 * @author 空城
 * @since 2021-07-29
 */
@Data
public class UserInfoQuery extends QueryParam {
    /**
     * 原类
     */
    private UserInfoDTO param;
    private QueryWrapper<UserInfo> query;
    private UpdateWrapper<UserInfo> update;
    private String isHaveBlob;


    private Integer notStatus;

    // likeParam
    // 账号
    private String userAccountIsLike;
    // 密码
    private String userPasswordIsLike;
    // 加密-盐
    private String userSaltIsLike;
    // 昵称
    private String userNicknameIsLike;
    // 真实姓名
    private String userRealNameIsLike;
    // 手机号
    private String userPhoneIsLike;
    // 身份证
    private String userIdNumberIsLike;
    // 用户头像
    private String userHeadImgIsLike;
    // 国家
    private String userCountryIsLike;
    // 省份
    private String userProvinceIsLike;
    // 城市
    private String userCityIsLike;
    // 县区
    private String userAreaIsLike;
    // 城镇
    private String userTownIsLike;
    // 详细地址
    private String userAddressIsLike;


    public QueryWrapper<UserInfo> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
        if (getParam() == null) {
            setParam(new UserInfoDTO());
        }


        //
        if (StringUtil.isNotEmpty(getParam().getUserId())) {
            query.eq("user_id", getParam().getUserId());
            update.eq("user_id", getParam().getUserId());
        }

        // 账号
        if (StringUtil.isNotEmpty(getParam().getUserAccount())) {
            query.eq("user_account", getParam().getUserAccount());
            update.eq("user_account", getParam().getUserAccount());
        }
        if (StringUtil.isNotEmpty(getUserAccountIsLike())) {
            query.like("user_account", getUserAccountIsLike());
            update.like("user_account", getUserAccountIsLike());
        }

        // 密码
        if (StringUtil.isNotEmpty(getParam().getUserPassword())) {
            query.eq("user_password", getParam().getUserPassword());
            update.eq("user_password", getParam().getUserPassword());
        }
        if (StringUtil.isNotEmpty(getUserPasswordIsLike())) {
            query.like("user_password", getUserPasswordIsLike());
            update.like("user_password", getUserPasswordIsLike());
        }

        // 加密-盐
        if (StringUtil.isNotEmpty(getParam().getUserSalt())) {
            query.eq("user_salt", getParam().getUserSalt());
            update.eq("user_salt", getParam().getUserSalt());
        }
        if (StringUtil.isNotEmpty(getUserSaltIsLike())) {
            query.like("user_salt", getUserSaltIsLike());
            update.like("user_salt", getUserSaltIsLike());
        }

        // 性别（0未知，1男，2女）
        if (StringUtil.isNotEmpty(getParam().getUserSex())) {
            query.eq("user_sex", getParam().getUserSex());
            update.eq("user_sex", getParam().getUserSex());
        }

        // 昵称
        if (StringUtil.isNotEmpty(getParam().getUserNickname())) {
            query.eq("user_nickname", getParam().getUserNickname());
            update.eq("user_nickname", getParam().getUserNickname());
        }
        if (StringUtil.isNotEmpty(getUserNicknameIsLike())) {
            query.like("user_nickname", getUserNicknameIsLike());
            update.like("user_nickname", getUserNicknameIsLike());
        }

        // 真实姓名
        if (StringUtil.isNotEmpty(getParam().getUserRealName())) {
            //System.out.println("*************************************   : "+getParam().getUserRealName());
            query.eq("user_real_name", getParam().getUserRealName());
            update.eq("user_real_name", getParam().getUserRealName());
        }
        if (StringUtil.isNotEmpty(getUserRealNameIsLike())) {
            query.like("user_real_name", getUserRealNameIsLike());
            update.like("user_real_name", getUserRealNameIsLike());
        }

        // 手机号
        if (StringUtil.isNotEmpty(getParam().getUserPhone())) {
            query.eq("user_phone", getParam().getUserPhone());
            update.eq("user_phone", getParam().getUserPhone());
        }
        if (StringUtil.isNotEmpty(getUserPhoneIsLike())) {
            query.like("user_phone", getUserPhoneIsLike());
            update.like("user_phone", getUserPhoneIsLike());
        }


        if (StringUtil.isNotEmpty(getUserIdNumberIsLike())) {
            query.like("user_id_number", getUserIdNumberIsLike());
            update.like("user_id_number", getUserIdNumberIsLike());
        }

        // 用户头像
        if (StringUtil.isNotEmpty(getParam().getUserHeadImg())) {
            query.eq("user_head_img", getParam().getUserHeadImg());
            update.eq("user_head_img", getParam().getUserHeadImg());
        }
        if (StringUtil.isNotEmpty(getUserHeadImgIsLike())) {
            query.like("user_head_img", getUserHeadImgIsLike());
            update.like("user_head_img", getUserHeadImgIsLike());
        }


        if (StringUtil.isNotEmpty(getUserCountryIsLike())) {
            query.like("user_country", getUserCountryIsLike());
            update.like("user_country", getUserCountryIsLike());
        }


        if (StringUtil.isNotEmpty(getUserProvinceIsLike())) {
            query.like("user_province", getUserProvinceIsLike());
            update.like("user_province", getUserProvinceIsLike());
        }

        if (StringUtil.isNotEmpty(getUserCityIsLike())) {
            query.like("user_city", getUserCityIsLike());
            update.like("user_city", getUserCityIsLike());
        }


        if (StringUtil.isNotEmpty(getUserTownIsLike())) {
            query.like("user_town", getUserTownIsLike());
            update.like("user_town", getUserTownIsLike());
        }

        if (StringUtil.isNotEmpty(getUserAddressIsLike())) {
            query.like("user_address", getUserAddressIsLike());
            update.like("user_address", getUserAddressIsLike());
        }

        //
        if (StringUtil.isNotEmpty(getParam().getUserCreateTime())) {
            query.eq("user_create_time", getParam().getUserCreateTime());
            update.eq("user_create_time", getParam().getUserCreateTime());
        }

        //
        if (StringUtil.isNotEmpty(getParam().getUserUpdateTime())) {
            query.eq("user_update_time", getParam().getUserUpdateTime());
            update.eq("user_update_time", getParam().getUserUpdateTime());
        }

        // 用户状态（见枚举）
        if (StringUtil.isNotEmpty(getParam().getUserStatus())) {
            query.eq("user_status", getParam().getUserStatus());
            update.eq("user_status", getParam().getUserStatus());
        }
        if (StringUtil.isNotEmpty(getNotStatus())) {
            query.ne("user_status", getNotStatus());
            update.ne("user_status", getNotStatus());
        }
// 排序
        if (StringUtil.isNotEmpty(getOrderItem())) {
            if (StringUtil.isEmpty(getOrderType())) {
                query.orderByAsc(getOrderItem().split(","));
            } else if ("asc".equals((getOrderType()))) {
                query.orderByAsc(getOrderItem().split(","));
            } else if ("desc".equals((getOrderType()))) {
                query.orderByDesc(getOrderItem().split(","));
            }
        }
        return query;
    }

    @Override
    public QueryWrapper<UserInfo> getQueryP() {
        return query;
    }

}
