package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.UserInfo;
import com.chenyx.entity.dto.UserInfoDTO;

import java.util.List;

/**
 * 用户信息 mapper自定义类
 *
 * @author 空城
 * @since 2021-07-29
*/
public interface ExtUserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<UserInfoDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    UserInfo getByIdForUpdate(Long id);

}
