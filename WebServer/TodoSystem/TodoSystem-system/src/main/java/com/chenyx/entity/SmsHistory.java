package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 短信验证记录表 sms_history
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 随机编码
     */
    private String randomCode;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 截止时间
     */
    private Date deadLine;


}
