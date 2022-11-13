package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.MiniProgram;
import com.chenyx.entity.dto.MiniProgramDTO;

import java.util.List;

/**
 * <p>
 * 小程序表 mini_program mapper自定义类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
public interface ExtMiniProgramMapper extends BaseMapper<MiniProgram> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<MiniProgramDTO> list);

}
