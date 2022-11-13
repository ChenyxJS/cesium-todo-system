package com.chenyx.entity.dto;

import com.chenyx.entity.SmsHistory;
import lombok.Data;

/**
 * <p>
 * 短信验证记录表 sms_history DTO类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
@Data
public class SmsHistoryDTO extends SmsHistory {
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
