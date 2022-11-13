package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 小程序表 mini_program
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MiniProgram implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mini_program_id", type = IdType.AUTO)
    private Long miniProgramId;

    /**
     * 小程序appid
     */
    private String miniAppid;

    /**
     * 小程序secret
     */
    private String miniSecret;

    /**
     * 小程序名称
     */
    private String miniAppName;

    /**
     * 记录创建日期时间
     */
    private Date miniDbCreateDate;

    /**
     * 记录最后一次修改日期时间
     */
    private Date miniDbUpdateDate;

    /**
     * 使用状态
     */
    private String miniStatus;


}
