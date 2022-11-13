package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyx
 * @since 2022-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "todo_id", type = IdType.AUTO)
    private Long todoId;

    /**
     * 标题
     */
    private String todoTitle;

    /**
     *  0: "Editor",1: "Unfinished",2: "Done",3: "Emergency",
     */
    private Integer todoType;

    /**
     * 经度
     */
    private Double todoLng;

    /**
     * 纬度
     */
    private Double todoLat;

    /**
     * 高度
     */
    private Double todoAlt;

    /**
     * 详细地址
     */
    private String todoAddress;

    /**
     * 详情信息
     */
    private String todoDesc;

    /**
     * 开始时间
     */
    private Date todoStartTime;

    /**
     * 结束时间
     */
    private Date todoEndTime;

    /**
     * 创建时间
     */
    private Date todoCreateTime;

    /**
     * 修改时间
     */
    private Date todoUpdateTime;


}
