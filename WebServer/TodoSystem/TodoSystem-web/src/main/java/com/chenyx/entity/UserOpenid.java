package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * user_openid_用户openid
 * </p>
 *
 * @author 苏友朋
 * @since 2021-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserOpenid implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uop_id", type = IdType.AUTO)
    private Long uopId;

    /**
     * 用户id
     */
    private Long uopUserId;

    /**
     * 用户openid
     */
    private String uopOpenid;

    /**
     * 公众平台类型
     */
    private String uopPlatform;

    /**
     * 小程序appid
     */
    private String uopAppid;

    /**
     * 备注
     */
    private String uopRemark;

    /**
     * 状态，1：在线、0、离职
     */
    private Integer uopStatus;

    /**
     * 数据创建时间
     */
    private Date uopDbCreateTime;

    /**
     * 数据最后一次修改时间
     */
    private Date uopDbUpdateTime;


}
