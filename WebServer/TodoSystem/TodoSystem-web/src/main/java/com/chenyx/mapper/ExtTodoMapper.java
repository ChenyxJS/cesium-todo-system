package com.chenyx.mapper;

import com.chenyx.entity.dto.TodoDTO;
import com.chenyx.entity.Todo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 *  mapper自定义类
 *
 * @author chenyx
 * @since 2022-11-13
*/
public interface ExtTodoMapper extends BaseMapper<Todo> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<TodoDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    Todo getByIdForUpdate(Long id);

}