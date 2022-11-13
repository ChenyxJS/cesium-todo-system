package com.chenyx.entity.dto;

import com.chenyx.entity.MiniProgram;
import lombok.Data;

/**
 * <p>
 * 小程序表 mini_program DTO类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
@Data
public class MiniProgramDTO extends MiniProgram {
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
