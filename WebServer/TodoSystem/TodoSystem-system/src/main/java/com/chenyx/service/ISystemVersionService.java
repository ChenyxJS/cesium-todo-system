package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.SystemVersion;
import com.chenyx.entity.dto.SystemVersionDTO;
import com.chenyx.entity.query.SystemVersionQuery;
import com.chenyx.util.JsonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public interface ISystemVersionService extends IService<SystemVersion> {
 /**
 * 分页查询考试项目
 *
 * @return JsonResult
 */
 JsonResult selectAll(SystemVersionQuery query);

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
 JsonResult updateByKey(SystemVersionDTO record);

 /**
 * 插入考试项目
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(SystemVersionDTO record);
}
