package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.Role;
import com.chenyx.entity.dto.RoleDTO;

import java.util.List;

/**
 *  mapper自定义类
 *
 * @author 空城
 * @since 2021-07-29
*/
public interface ExtRoleMapper extends BaseMapper<Role> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<RoleDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    Role getByIdForUpdate(Long id);

}
