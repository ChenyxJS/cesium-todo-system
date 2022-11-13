package com.chenyx.entity.dto;

import com.chenyx.entity.UserOpenid;
import lombok.Data;

/**
 * user_openid_用户openid DTO类
 *
 * @author 苏友朋
 * @since 2021-07-30
*/
@Data
public class UserOpenidDTO extends UserOpenid {
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
