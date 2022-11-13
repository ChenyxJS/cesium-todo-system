package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.SystemLog;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
public interface SystemLogMapper extends BaseMapper<SystemLog> {
    int insert(SystemLog record);

    int insertSelective(SystemLog record);
}
