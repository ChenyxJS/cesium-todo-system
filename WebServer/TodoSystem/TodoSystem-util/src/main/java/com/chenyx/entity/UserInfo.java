package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author 空城
 * @since 2021-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 加密-盐
     */
    private String userSalt;

    /**
     * 性别（0未知，1男，2女）
     */
    private Integer userSex;

    /**
     * 昵称
     */
    private String userNickname;

    /**
     * 真实姓名
     */
    private String userRealName;

    /**
     * 手机号
     */
    private String userPhone;


    /**
     * 用户头像
     */
    private String userHeadImg;



    private Date userCreateTime;

    private Date userUpdateTime;

    /**
     * 用户状态（见枚举）
     */
    private Integer userStatus;

    /**
     * 是否删除（1是）
     */
    private Integer userDelete;

    /**
     * 上次登录角色id
     */
    private Long userLastRoleId;

    /**
     * 上次登录角色
     */
    private String userLastRoleName;



    /**
     * 上次登录时间
     */
    private Date userLastLoginTime;


}
