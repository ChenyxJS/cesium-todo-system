package com.chenyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.SystemConfigValue;
import com.chenyx.entity.dto.SystemConfigValueDTO;
import com.chenyx.entity.query.SystemConfigValueQuery;
import com.chenyx.mapper.ExtSystemConfigValueMapper;
import com.chenyx.service.ISystemConfigValueService;
import com.chenyx.util.JsonResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统设置值表system_config_value 服务实现类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Service
public class SystemConfigValueServiceImpl extends ServiceImpl<ExtSystemConfigValueMapper, SystemConfigValue> implements ISystemConfigValueService {


    /**
     * 分页查询系统设置值表system_config_value
     *
     * @return JsonResult
     */
    @Override
    public JsonResult selectAll(SystemConfigValueQuery query) {
        return query.queryJsonResult(this, SystemConfigValueDTO.class);
    }

    /**
     * 根据主键删除系统设置值表system_config_value
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
     * 根据主键修改系统设置值表system_config_value
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @Override
    public JsonResult updateByKey(SystemConfigValueDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (updateById(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入系统设置值表system_config_value
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @Override
    public JsonResult add(SystemConfigValueDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (save(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    @Override
    public List<SystemConfigValue> getConfigValueByCode(String code) {
        QueryWrapper<SystemConfigValue> systemConfigValueQueryWrapper = new QueryWrapper<>();
        systemConfigValueQueryWrapper.lambda().eq(SystemConfigValue::getScvaCode, code);
        return list(systemConfigValueQueryWrapper);
    }

}
