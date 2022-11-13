package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统设置表system_config
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "system_config_id", type = IdType.AUTO)
    private Long systemConfigId;

    /**
     * 设置名
     */
    private String sycoName;

    /**
     * 设置编码
     */
    private String sycoCode;

    /**
     * 类型
     */
    private String sycoType;

    /**
     * 对应几个值：one：一个   more：多个
     */
    private String sycoValueCount;

    /**
     * Y/N(该类型是否可编辑)
     */
    private String sycoTypeCanEd;

    /**
     * Y/N(该类型对应的值是否可编辑)
     */
    private String sycoValueCanEd;

    /**
     * 状态
     */
    private String sycoStatus;

    /**
     * 记录创建日期时间
     */
    private Date sycoDbCreateDate;

    /**
     * 记录最后一次修改日期时间
     */
    private Date sycoDbUpdateDate;


}
