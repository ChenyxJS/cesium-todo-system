package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.Permission;
import com.chenyx.entity.dto.PermissionDTO;

import java.util.List;

/**
 * 权限表 mapper自定义类
 *
 * @author 空城
 * @since 2021-07-29
*/
public interface ExtPermissionMapper extends BaseMapper<Permission> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<PermissionDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    Permission getByIdForUpdate(Long id);

}
