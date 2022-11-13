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
 * @author 空城
 * @since 2021-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "usro_id", type = IdType.AUTO)
    private Long usroId;

    /**
     * 用户id
     */
    private Long usroUserId;

    /**
     * 角色id
     */
    private Long usroRoleId;

    private Date usroCreateTime;

    private Date usroUpdateTime;

    /**
     * 机构id
     */
    private Long usroOrgId;

    /**
     * 机构名称
     */
    private String usroOrgName;


}
