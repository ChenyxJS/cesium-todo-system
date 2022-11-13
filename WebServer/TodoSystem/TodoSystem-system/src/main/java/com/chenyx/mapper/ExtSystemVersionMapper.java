package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.SystemVersion;
import com.chenyx.entity.dto.SystemVersionDTO;

import java.util.List;

/**
 * <p>
 *  mapper自定义类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ExtSystemVersionMapper extends BaseMapper<SystemVersion> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<SystemVersionDTO> list);

}
