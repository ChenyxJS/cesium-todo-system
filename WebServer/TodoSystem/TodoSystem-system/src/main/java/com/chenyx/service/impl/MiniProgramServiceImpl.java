package com.chenyx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.MiniProgram;
import com.chenyx.entity.dto.MiniProgramDTO;
import com.chenyx.entity.query.MiniProgramQuery;
import com.chenyx.mapper.ExtMiniProgramMapper;
import com.chenyx.service.IMiniProgramService;
import com.chenyx.util.JsonResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小程序表 mini_program 服务实现类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
 */
@Service
public class MiniProgramServiceImpl extends ServiceImpl<ExtMiniProgramMapper, MiniProgram> implements IMiniProgramService {


    /**
     * 分页查询小程序表 mini_program
     *
     * @return JsonResult
     */

    @Override
    public JsonResult selectAll(MiniProgramQuery query) {
        return query.queryJsonResult(this, MiniProgramDTO.class);
    }

    /**
     * 根据主键删除小程序表 mini_program
     *
     * @param id 主键
     * @return JsonResult
     */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        if (removeById(id)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改小程序表 mini_program
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @Override
    public JsonResult updateByKey(MiniProgramDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (updateById(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入小程序表 mini_program
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @Override
    public JsonResult add(MiniProgramDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (save(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

}
