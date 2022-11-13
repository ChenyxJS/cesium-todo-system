package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.SystemConfig;
import com.chenyx.entity.SystemConfigValue;
import com.chenyx.entity.dto.SystemConfigDTO;
import com.chenyx.entity.query.SystemConfigQuery;
import com.chenyx.util.JsonResult;

import java.util.List;

/**
 * <p>
 * 系统设置表system_config 服务类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ISystemConfigService extends IService<SystemConfig> {
 /**
 * 分页查询考试项目
 *
 * @return JsonResult
 */
 JsonResult selectAll(SystemConfigQuery query);

 /**
 * 根据主键删除考试项目
 *
 * @param id 主键
 * @return JsonResult
 */
 JsonResult deleteByKey(Long id);

 /**
 * 根据主键修改考试项目
 *
 * @param record 修改信息
 * @return JsonResult
 */
 JsonResult updateByKey(SystemConfigDTO record);

 /**
 * 插入考试项目
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(SystemConfigDTO record);


 /**
  * 根据系统设置编号，获取系统设置值
  *
  * @param configNumber 系统设置编号
  * @return JsonResult
  */
 List<SystemConfigValue> getSystemConfigValueByConfigNumber(String configNumber);
}
