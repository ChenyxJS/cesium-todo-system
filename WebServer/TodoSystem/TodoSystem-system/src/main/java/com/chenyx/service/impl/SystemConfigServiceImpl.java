package com.chenyx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.SystemConfig;
import com.chenyx.entity.SystemConfigValue;
import com.chenyx.entity.dto.SystemConfigDTO;
import com.chenyx.entity.dto.SystemConfigValueDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.SystemConfigQuery;
import com.chenyx.entity.query.SystemConfigValueQuery;
import com.chenyx.mapper.ExtSystemConfigMapper;
import com.chenyx.service.ISystemConfigService;
import com.chenyx.service.ISystemConfigValueService;
import com.chenyx.util.EntityUtil;
import com.chenyx.util.JsonResult;
import com.chenyx.util.ListUtil;
import com.chenyx.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统设置表system_config 服务实现类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Service
public class SystemConfigServiceImpl extends ServiceImpl<ExtSystemConfigMapper, SystemConfig> implements ISystemConfigService {
    @Resource
    private ISystemConfigValueService systemConfigValueService;

    /**
     * 分页查询系统设置表system_config
     *
     * @return JsonResult
     */
    @Override
    public JsonResult selectAll(SystemConfigQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        IPage<SystemConfig> page = query.queryPage(this);
        List<SystemConfigDTO> records = EntityUtil.parentListToChildList(page.getRecords(), SystemConfigDTO.class);
        if (ListUtil.isNotEmpty(records)) {
            if (StringUtil.isNotEmpty(query.getIsHaveValue()) && "Y".equals(query.getIsHaveValue())) {
                // 拼装系统设置id列表
                List<Long> configIds = new ArrayList<>();
                Map<Long, SystemConfigDTO> systemConfigDTOMap = new HashMap<>();
                records.forEach(v -> {
                    configIds.add(v.getSystemConfigId());
                    systemConfigDTOMap.put(v.getSystemConfigId(),v);
                    v.setSystemConfigValues(new ArrayList<>());
                });
                if (ListUtil.isNotEmpty(configIds)) {
                    // 查询全部值信息
                    SystemConfigValueQuery systemConfigValueQuery = new SystemConfigValueQuery();
                    systemConfigValueQuery.setConfigIds(configIds);
                    List<SystemConfigValue> systemConfigValues = systemConfigValueService.list(systemConfigValueQuery.buildExample());
                    if (ListUtil.isNotEmpty(systemConfigValues)) {
                        systemConfigValues.forEach(v->{
                            if(systemConfigDTOMap.get(v.getScvaTypeId()) != null){
                                systemConfigDTOMap.get(v.getScvaTypeId()).getSystemConfigValues().add(v);
                            }
                        });
                    }
                }
            }
            jsonResult.buildTrueNew(page.getTotal(), records);
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }



    /**
     * 根据主键删除系统设置表system_config
     *
     * @param id 主键
     * @return JsonResult
     */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        if (removeById(id)) {
            // 删除对应的设置值
            SystemConfigValueQuery systemConfigValueQuery = new SystemConfigValueQuery();
            systemConfigValueQuery.setParam(new SystemConfigValueDTO());
            systemConfigValueQuery.getParam().setScvaTypeId(id);
            systemConfigValueService.remove(systemConfigValueQuery.buildExample());
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改系统设置表system_config
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @Override
    public JsonResult updateByKey(SystemConfigDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (updateById(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入系统设置表system_config
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @Override
    public JsonResult add(SystemConfigDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (save(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据系统设置编号，获取系统设置值
     *
     * @param configNumber 系统设置编号
     * @return JsonResult
     */
    @Override
    public List<SystemConfigValue> getSystemConfigValueByConfigNumber(String configNumber) {
        // 根据设置值编号获取设置信息
        SystemConfigQuery systemConfigQuery = new SystemConfigQuery();
        systemConfigQuery.setParam(new SystemConfigDTO());
        systemConfigQuery.getParam().setSycoCode(configNumber);
        List<SystemConfig> systemConfigs = list(systemConfigQuery.buildExample());
        if(ListUtil.isEmpty(systemConfigs) || systemConfigs.size() != 1){
            return null;
        }
        SystemConfigValueQuery systemConfigValueQuery = new SystemConfigValueQuery();
        systemConfigValueQuery.setParam(new SystemConfigValueDTO());
        systemConfigValueQuery.getParam().setScvaTypeId(systemConfigs.get(0).getSystemConfigId());
        return systemConfigValueService.list(systemConfigValueQuery.buildExample());
    }

}
