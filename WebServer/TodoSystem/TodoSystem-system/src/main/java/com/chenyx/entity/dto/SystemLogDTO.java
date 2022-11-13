package com.chenyx.entity.dto;

import com.chenyx.entity.SystemLog;
import lombok.Data;

/**
 * <p>
 *  DTO类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
@Data
public class SystemLogDTO extends SystemLog {
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
