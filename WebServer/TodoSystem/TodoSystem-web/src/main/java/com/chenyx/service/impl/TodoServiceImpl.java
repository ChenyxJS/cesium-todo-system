package com.chenyx.service.impl;

import com.chenyx.entity.dto.TodoDTO;
import com.chenyx.entity.Todo;
import com.chenyx.mapper.ExtTodoMapper;
import com.chenyx.service.ITodoService;
import com.chenyx.entity.enums.RequestEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.chenyx.entity.query.TodoQuery;
import com.chenyx.util.JsonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenyx.util.ListUtil;
import java.util.List;
import com.chenyx.util.EntityUtil;

/**
 *  服务实现类
 *
 * @author chenyx
 * @since 2022-11-13
 */
@Service
public class TodoServiceImpl extends ServiceImpl<ExtTodoMapper, Todo> implements ITodoService {


    /**
     * 分页查询
     *
     * @return JsonResult
    */
    @Override
    public JsonResult selectAll(TodoQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        Page<Todo> page = query.buildPage(this);
        List<TodoDTO> records = EntityUtil.parentListToChildList(page.getRecords(), TodoDTO.class);
        if(ListUtil.isNotEmpty(records)){
            jsonResult.buildTrueNew(page.getTotal(),records);
        }else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return JsonResult
    */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        if(removeById(id)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @Override
    public JsonResult updateByKey(TodoDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(updateById(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @Override
    public JsonResult add(TodoDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(save(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

}
