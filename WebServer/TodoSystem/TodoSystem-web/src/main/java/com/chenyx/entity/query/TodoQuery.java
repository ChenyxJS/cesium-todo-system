package com.chenyx.entity.query;

import com.chenyx.util.StringUtil;
import com.chenyx.entity.dto.TodoDTO;
import com.chenyx.entity.Todo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.Data;

/**
 *  查询类
 *
 * @author chenyx
 * @since 2022-11-13
*/
@Data
public class TodoQuery extends QueryParam {
    /**
     * 原类
     */
    private TodoDTO param;
    private QueryWrapper<Todo> query;
    private UpdateWrapper<Todo> update;
    private String isHaveBlob;

    // likeParam
    // 标题
    private String todoTitleIsLike;
    // 详细地址
    private String todoAddressIsLike;
    // 详情信息
    private String todoDescIsLike;

    public QueryWrapper<Todo> buildExample() {
        query = new QueryWrapper<>();
        update = new UpdateWrapper<>();
            if(getParam() == null){
                setParam(new TodoDTO());
            }


    // 
        if(StringUtil.isNotEmpty(getParam().getTodoId())){
            query.eq("todo_id",getParam().getTodoId());
            update.eq("todo_id",getParam().getTodoId());
        }

    // 标题
        if(StringUtil.isNotEmpty(getParam().getTodoTitle())){
            query.eq("todo_title",getParam().getTodoTitle());
            update.eq("todo_title",getParam().getTodoTitle());
        }
        if(StringUtil.isNotEmpty(getTodoTitleIsLike())){
            query.like("todo_title",getTodoTitleIsLike());
            update.like("todo_title",getTodoTitleIsLike());
        }

    //  0: "Editor",1: "Unfinished",2: "Done",3: "Emergency",
        if(StringUtil.isNotEmpty(getParam().getTodoType())){
            query.eq("todo_type",getParam().getTodoType());
            update.eq("todo_type",getParam().getTodoType());
        }

    // 经度
        if(StringUtil.isNotEmpty(getParam().getTodoLng())){
            query.eq("todo_lng",getParam().getTodoLng());
            update.eq("todo_lng",getParam().getTodoLng());
        }

    // 纬度
        if(StringUtil.isNotEmpty(getParam().getTodoLat())){
            query.eq("todo_lat",getParam().getTodoLat());
            update.eq("todo_lat",getParam().getTodoLat());
        }

    // 高度
        if(StringUtil.isNotEmpty(getParam().getTodoAlt())){
            query.eq("todo_alt",getParam().getTodoAlt());
            update.eq("todo_alt",getParam().getTodoAlt());
        }

    // 详细地址
        if(StringUtil.isNotEmpty(getParam().getTodoAddress())){
            query.eq("todo_address",getParam().getTodoAddress());
            update.eq("todo_address",getParam().getTodoAddress());
        }
        if(StringUtil.isNotEmpty(getTodoAddressIsLike())){
            query.like("todo_address",getTodoAddressIsLike());
            update.like("todo_address",getTodoAddressIsLike());
        }

    // 详情信息
        if(StringUtil.isNotEmpty(getParam().getTodoDesc())){
            query.eq("todo_desc",getParam().getTodoDesc());
            update.eq("todo_desc",getParam().getTodoDesc());
        }
        if(StringUtil.isNotEmpty(getTodoDescIsLike())){
            query.like("todo_desc",getTodoDescIsLike());
            update.like("todo_desc",getTodoDescIsLike());
        }

    // 开始时间
        if(StringUtil.isNotEmpty(getParam().getTodoStartTime())){
            query.eq("todo_start_time",getParam().getTodoStartTime());
            update.eq("todo_start_time",getParam().getTodoStartTime());
        }

    // 结束时间
        if(StringUtil.isNotEmpty(getParam().getTodoEndTime())){
            query.eq("todo_end_time",getParam().getTodoEndTime());
            update.eq("todo_end_time",getParam().getTodoEndTime());
        }

    // 创建时间
        if(StringUtil.isNotEmpty(getParam().getTodoCreateTime())){
            query.eq("todo_create_time",getParam().getTodoCreateTime());
            update.eq("todo_create_time",getParam().getTodoCreateTime());
        }

    // 修改时间
        if(StringUtil.isNotEmpty(getParam().getTodoUpdateTime())){
            query.eq("todo_update_time",getParam().getTodoUpdateTime());
            update.eq("todo_update_time",getParam().getTodoUpdateTime());
        }
// 排序
        if(StringUtil.isNotEmpty(getOrderItem())){
            if(StringUtil.isEmpty(getOrderType())){
                query.orderByAsc(getOrderItem().split(","));
            }else if("asc".equals((getOrderType()))){
                query.orderByAsc(getOrderItem().split(","));
            }else if("desc".equals((getOrderType()))){
                query.orderByDesc(getOrderItem().split(","));
            }
        }
        return query;
    }

    @Override
    public QueryWrapper<Todo> getQueryP() {
        return query;
    }

}