package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.UserOpenid;
import com.chenyx.entity.dto.UserOpenidDTO;

import java.util.List;

/**
 * user_openid_用户openid mapper自定义类
 *
 * @author 苏友朋
 * @since 2021-07-30
*/
public interface ExtUserOpenidMapper extends BaseMapper<UserOpenid> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<UserOpenidDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    UserOpenid getByIdForUpdate(Long id);

}
