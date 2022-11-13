package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户通知记录
 * </p>
 *
 * @author 空城
 * @since 2021-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "usno_id", type = IdType.AUTO)
    private Long usnoId;

    private Long usnoNoticeId;

    private Long usnoUserId;

    /**
     * 状态（0未读，1已读-具体看枚举）
     */
    private Integer usnoStatus;

    private Date usnoCreateTime;

    private Date usnoReadTime;

    /**
     * 预警库id
     */
    private Long usnoSwreId;
}
