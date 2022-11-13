package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.UserOpenid;
import com.chenyx.entity.dto.UserOpenidDTO;
import com.chenyx.entity.query.UserOpenidQuery;
import com.chenyx.util.JsonResult;

/**
 * user_openid_用户openid 服务类
 *
 * @author 苏友朋
 * @since 2021-07-30
*/
public interface IUserOpenidService extends IService<UserOpenid> {
 /**
 * 分页查询user_openid_用户openid
 *
 * @return JsonResult
 */
 JsonResult selectAll(UserOpenidQuery query);

 /**
 * 根据主键删除user_openid_用户openid
 *
 * @param id 主键
 * @return JsonResult
 */
 JsonResult deleteByKey(Long id);

 /**
 * 根据主键修改user_openid_用户openid
 *
 * @param record 修改信息
 * @return JsonResult
 */
 JsonResult updateByKey(UserOpenidDTO record);

 /**
 * 插入user_openid_用户openid
 *
 * @param record 插入信息
 * @return JsonResult
 */
 JsonResult add(UserOpenidDTO record);
}
