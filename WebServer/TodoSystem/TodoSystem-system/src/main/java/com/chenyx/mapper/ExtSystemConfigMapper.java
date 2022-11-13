package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.SystemConfig;
import com.chenyx.entity.dto.SystemConfigDTO;

import java.util.List;

/**
 * <p>
 * 系统设置表system_config mapper自定义类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ExtSystemConfigMapper extends BaseMapper<SystemConfig> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<SystemConfigDTO> list);

}
