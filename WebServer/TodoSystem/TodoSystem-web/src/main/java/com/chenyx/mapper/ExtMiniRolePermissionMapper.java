package com.chenyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyx.entity.MiniRolePermission;
import com.chenyx.entity.dto.MiniRolePermissionDTO;

import java.util.List;

/**
 *  mapper自定义类
 *
 * @author xu.wang
 * @since 2021-08-02
*/
public interface ExtMiniRolePermissionMapper extends BaseMapper<MiniRolePermission> {

    /**
     * 批量插入
     * @param list  数据
     * @return  int
    */
    int batchAdd(List<MiniRolePermissionDTO> list);

    /**
     * 加锁查询
     *
     * @param id 主键
     * @return Address
    */
    MiniRolePermission getByIdForUpdate(Long id);

}
