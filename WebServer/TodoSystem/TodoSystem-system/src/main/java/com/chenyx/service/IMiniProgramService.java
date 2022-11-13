package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.MiniProgram;
import com.chenyx.entity.dto.MiniProgramDTO;
import com.chenyx.entity.query.MiniProgramQuery;
import com.chenyx.util.JsonResult;

/**
 * <p>
 * 小程序表 mini_program 服务类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
public interface IMiniProgramService extends IService<MiniProgram> {
 /**
 * 分页查询考试项目
 *
 * @return JsonResult
 */
 JsonResult selectAll(MiniProgramQuery query);

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
 JsonResult updateByKey(MiniProgramDTO record);

 /**
 * 插入考试项目
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(MiniProgramDTO record);
}
