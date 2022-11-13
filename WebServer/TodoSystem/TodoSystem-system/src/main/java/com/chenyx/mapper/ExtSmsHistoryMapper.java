package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.SmsHistory;
import com.chenyx.entity.dto.SmsHistoryDTO;

import java.util.List;

/**
 * <p>
 * 短信验证记录表 sms_history mapper自定义类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
public interface ExtSmsHistoryMapper extends BaseMapper<SmsHistory> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<SmsHistoryDTO> list);

}
