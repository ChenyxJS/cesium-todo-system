package com.chenyx.entity.dto;

import com.chenyx.entity.UserInfo;
import lombok.Data;

/**
 * 用户信息 DTO类
 *
 * @author 空城
 * @since 2021-07-29
*/
@Data
public class UserInfoDTO extends UserInfo {

    /**
     * 机构Id
     */
    private Long orgId;
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
