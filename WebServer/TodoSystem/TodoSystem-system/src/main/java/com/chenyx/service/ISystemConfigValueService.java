package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.SystemConfigValue;
import com.chenyx.entity.dto.SystemConfigValueDTO;
import com.chenyx.entity.query.SystemConfigValueQuery;
import com.chenyx.util.JsonResult;

import java.util.List;

/**
 * <p>
 * 系统设置值表system_config_value 服务类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ISystemConfigValueService extends IService<SystemConfigValue> {
 /**
 * 分页查询考试项目
 *
 * @return JsonResult
 */
 JsonResult selectAll(SystemConfigValueQuery query);

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
 JsonResult updateByKey(SystemConfigValueDTO record);

 /**
 * 插入考试项目
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(SystemConfigValueDTO record);

 /**
  * 根据code 获取配置
  * @param code
  * @return
  */
 List<SystemConfigValue> getConfigValueByCode(String code);
}
