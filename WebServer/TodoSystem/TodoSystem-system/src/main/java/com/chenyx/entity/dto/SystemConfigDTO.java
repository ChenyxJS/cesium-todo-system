package com.chenyx.entity.dto;

import com.chenyx.entity.SystemConfig;
import com.chenyx.entity.SystemConfigValue;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 系统设置表system_config DTO类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
@Data
public class SystemConfigDTO extends SystemConfig {
    /**
     * 设置值列表
     */
    private List<SystemConfigValue> systemConfigValues;
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
