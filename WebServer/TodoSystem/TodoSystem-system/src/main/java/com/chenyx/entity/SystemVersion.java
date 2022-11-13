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
public class SystemVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "system_version_id", type = IdType.AUTO)
    private Long systemVersionId;

    /**
     * 版本号变更操作员
     */
    private Long syveOperationUserId;

    /**
     * 版本号变更操作IP地址
     */
    private String syveOperationIp;

    /**
     * 版本号
     */
    private String syveNumber;

    /**
     * 上级版本（就是上次版本）
     */
    private Long syveParentId;

    /**
     * 版本号类型：固定为：小程序：mini      后台管理：admin      移动app：flutter     收银端：cashier
     */
    private String syveType;

    /**
     * 状态：使用中：useing        历史记录：history      还未使用的：future
     */
    private String syveStatus;

    /**
     * 版本简介（简单介绍）
     */
    private String syveLog;

    /**
     * 创建日期时间
     */
    private Date syveDbCreateDate;

    /**
     * 修改日期时间
     */
    private Date syveDbUpdateDate;

    /**
     * 更新包地址（用于收银端和移动端）
     */
    private String syveUpdatePackUrl;


}
