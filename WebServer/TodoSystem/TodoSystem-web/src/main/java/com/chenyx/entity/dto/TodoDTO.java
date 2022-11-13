package com.chenyx.entity.dto;

import com.chenyx.entity.Todo;
import lombok.Data;
/**
 *  DTO类
 *
 * @author chenyx
 * @since 2022-11-13
*/
@Data
public class TodoDTO extends Todo {
    /**
     * 验证添加方法参数空值
     *
     * @return Boolean
     */
    public Boolean verifyAddNullAttribute() {
        return true;
    }

    /**
     * 验证修改方法参数空值
     *
     * @return Boolean
     */
    public Boolean verifyUpdateNullAttribute() {
        return true;
    }
}
