package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.excel.poi.annotation.ImportField;
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
    @ImportField
    private String userAccount;

    /**
     * 密码
     */
    @ImportField
    private String userPassword;

    /**
     * 加密-盐
     */
    @ImportField
    private String userSalt;

    /**
     * 性别（0未知，1男，2女）
     */
    @ImportField
    private Integer userSex;

    /**
     * 昵称
     */
    @ImportField
    private String userNickname;

    /**
     * 真实姓名
     */
    @ImportField
    private String userRealName;

    /**
     * 手机号
     */
    @ImportField
    private String userPhone;

    /**
     * 身份证
     */
    @ImportField
    private String userIdNumber;

    /**
     * 用户头像
     */
    @ImportField
    private String userHeadImg;

    /**
     * 国家
     */
    @ImportField
    private String userCountry;

    /**
     * 省份
     */
    @ImportField
    private String userProvince;

    /**
     * 城市
     */
    @ImportField
    private String userCity;

    /**
     * 县区
     */
    @ImportField
    private String userArea;

    /**
     * 城镇
     */
    @ImportField
    private String userTown;

    /**
     * 详细地址
     */
    @ImportField
    private String userAddress;


    private Date userCreateTime;

    private Date userUpdateTime;

    /**
     * 用户状态（见枚举）
     */
    private Integer userStatus;



}
