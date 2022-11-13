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
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rope_id", type = IdType.AUTO)
    private Long ropeId;

    /**
     * 角色id
     */
    private Long ropeRoleId;

    /**
     * 权限id
     */
    private Long ropePermId;

    /**
     * 机构id
     */
    private Long ropeOrgId;

    private Date ropeCreateTime;

    private Date ropeUpdateTime;


}
