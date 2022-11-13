package com.chenyx.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * user_log（用户日志）
 * </p>
 *
 * @author 李清清
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long usloUserId;

    /**
     * 用户姓名
     */
    private String usloUserName;

    /**
     * 访问Ip
     */
    private String usloVisitAddress;

    /**
     * 服务器IP
     */
    private String usloServerAddress;

    /**
     * 机器名
     */
    private String usloHostName;

    /**
     * 项目id
     */
    private Long usloItemId;

    /**
     * 项目名称
     */
    private String usloItemName;

    /**
     * 请求url
     */
    private String usloUrl;

    /**
     * 请求端口
     */
    private String usloPor;

    /**
     * 请求类型（1.1post 2.get3. put4.delete）
     */
    private Integer usloType;

    /**
     * 方法描述
     */
    private String usloDescription;

    /**
     *  请求标识(1.http 2. https  3.tcp  4.udp)
     */
    private Integer usloIdentification;

    /**
     * 请求域名
     */
    private String usloDomain;

    /**
     * 请求时长
     */
    private Long usloRequestTime;

    /**
     * 请求状态
     */
    private Integer usloActionCode;

    /**
     * 错误类型
     */
    private String usloErrorName;

    /**
     * 错误内容
     */
    private String usloErrorContent;

    /**
     * 备注
     */
    private String usloRemark;

    /**
     * 访问开始时间
     */
    private Date usloDbStartTime;

    /**
     * 访问结束时间
     */
    private Date usloDbEndTime;

    /**
     *  创建时间
     */
    private Date usloDbCreateTime;

    /**
     * Date数据最后一次修改时间
     */
    private Date usloDbUpdateTime;

    /**
     * 请求方法名
     */
    private String usloMethodName;

    /**
     * 请求信息
     */
    private String usloRequestData;

    /**
     * 请求返回的数据
     */
    private String usloResponseData;
}
