package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.SystemConfigValue;
import com.chenyx.entity.dto.SystemConfigValueDTO;

import java.util.List;

/**
 * <p>
 * 系统设置值表system_config_value mapper自定义类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ExtSystemConfigValueMapper extends BaseMapper<SystemConfigValue> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<SystemConfigValueDTO> list);

}
