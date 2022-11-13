package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统设置值表system_config_value
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemConfigValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "system_config_value_id", type = IdType.AUTO)
    private Long systemConfigValueId;

    /**
     * 系统设置id
     */
    private Long scvaTypeId;

    /**
     * value
     */
    private String scvaValue;

    /**
     * 编码
     */
    private String scvaCode;

    /**
     * 状态
     */
    private String scvaStatus;

    /**
     * 记录创建日期时间
     */
    private Date scvaDbCreateDate;

    /**
     * 记录最后一次修改日期时间
     */
    private Date scvaDbUpdateDate;


}
