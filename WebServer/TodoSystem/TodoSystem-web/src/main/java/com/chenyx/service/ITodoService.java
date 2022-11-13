package com.chenyx.service;

import com.chenyx.entity.dto.TodoDTO;
import com.chenyx.entity.Todo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.query.TodoQuery;
import com.chenyx.util.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *  服务类
 *
 * @author chenyx
 * @since 2022-11-13
*/
public interface ITodoService extends IService<Todo> {
 /**
 * 分页查询
 *
 * @return JsonResult
 */
 JsonResult selectAll(TodoQuery query);

 /**
 * 根据主键删除
 *
 * @param id 主键
 * @return JsonResult
 */
 JsonResult deleteByKey(Long id);

 /**
 * 根据主键修改
 *
 * @param record 修改信息
 * @return JsonResult
 */
 JsonResult updateByKey(TodoDTO record);

 /**
 * 插入
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(TodoDTO record);
}
