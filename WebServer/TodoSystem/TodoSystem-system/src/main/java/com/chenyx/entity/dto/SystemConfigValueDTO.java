package com.chenyx.entity.dto;

import com.chenyx.entity.SystemConfigValue;
import lombok.Data;

/**
 * <p>
 * 系统设置值表system_config_value DTO类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
@Data
public class SystemConfigValueDTO extends SystemConfigValue {
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
