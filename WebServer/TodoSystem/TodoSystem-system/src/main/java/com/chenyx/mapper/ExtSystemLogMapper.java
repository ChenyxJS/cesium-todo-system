package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.SystemLog;
import com.chenyx.entity.dto.SystemLogDTO;

import java.util.List;

/**
 * <p>
 *  mapper自定义类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ExtSystemLogMapper extends BaseMapper<SystemLog> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<SystemLogDTO> list);

}
