package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.UserRole;
import com.chenyx.entity.dto.UserRoleDTO;

import java.util.List;

/**
 *  mapper自定义类
 *
 * @author xu.wang
 * @since 2021-09-13
*/
public interface ExtUserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<UserRoleDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    UserRole getByIdForUpdate(Long id);

}
