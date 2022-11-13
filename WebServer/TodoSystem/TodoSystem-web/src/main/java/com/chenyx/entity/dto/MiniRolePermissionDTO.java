package com.chenyx.entity.dto;

import com.chenyx.entity.MiniRolePermission;
import lombok.Data;

/**
 *  DTO类
 *
 * @author xu.wang
 * @since 2021-08-02
*/
@Data
public class MiniRolePermissionDTO extends MiniRolePermission {
    /**
     * 验证添加方法参数空值
     *
     * @return Boolean
     */
    public Boolean verifyAddNullAttribute() {
        return true;
    }

    /**
     * 验证修改方法参数空值
     *
     * @return Boolean
     */
    public Boolean verifyUpdateNullAttribute() {
        return true;
    }
}
