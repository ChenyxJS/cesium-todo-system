package com.chenyx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.SystemLog;
import com.chenyx.entity.dto.SystemLogDTO;
import com.chenyx.entity.query.SystemLogQuery;
import com.chenyx.mapper.ExtSystemLogMapper;
import com.chenyx.service.ISystemLogService;
import com.chenyx.util.JsonResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<ExtSystemLogMapper, SystemLog> implements ISystemLogService {


    /**
     * 分页查询
     *
     * @return JsonResult
    */
    @Override
    public JsonResult selectAll(SystemLogQuery query) {
        return query.queryJsonResult(this,SystemLogDTO.class);
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
    public JsonResult updateByKey(SystemLogDTO record) {
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
    public JsonResult add(SystemLogDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(save(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

}
