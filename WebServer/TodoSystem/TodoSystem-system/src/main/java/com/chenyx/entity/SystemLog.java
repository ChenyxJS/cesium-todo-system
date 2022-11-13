package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sylo_id", type = IdType.AUTO)
    private Long syloId;

    /**
     * 登录用户id
     */
    private Long syloLoginUserId;

    /**
     * 请求者ip
     */
    private String syloIpAddress;

    /**
     * 请求的数据
     */
    private String syloRequestData;

    /**
     * 返回的数据
     */
    private String syloResponseData;

    /**
     * 创建时间
     */
    private Date syloCreateTime;

    /**
     * 描述
     */
    private String syloDescription;

    /**
     * 请求地址
     */
    private String syloRequestUrl;

    /**
     * 请求响应时间
     */
    private Long syloRequestTime;

    /**
     * 方法名称
     */
    private String syloMethodName;


}
