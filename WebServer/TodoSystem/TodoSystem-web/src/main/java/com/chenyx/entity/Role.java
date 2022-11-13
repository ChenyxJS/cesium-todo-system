package com.chenyx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.chenyx.entity.tree.MyTree;
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
public class Role extends MyTree<Role> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 父节点id
     */
    private Long rolePId;

    /**
     * 层级关系
     */
    private String rolePCode;

    /**
     * 名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 类型（见枚举）
     */
    private Integer roleType;

    /**
     * 备注
     */
    private String roleRemark;

    /**
     * 是否删除（1删除）
     */
    private Integer roleIsDelete;

    /**
     * 状态（见枚举）
     */
    private Integer roleStatus;

    private Date roleCreateTime;

    private Date roleUpdateTime;
    /**
     * 机构id，主要给登录信息使用
     */
    @TableField(exist = false)
    private Long orgId;

    @Override
    public void setIdAndPid() {
        setId(getRoleId());
        setPId(getRolePId());
        setName(getRoleName());
    }
}
