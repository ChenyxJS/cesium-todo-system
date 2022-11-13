package com.chenyx.entity.enums;

/**
 * <p>
 * 小程序表 mini_program 枚举类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
public enum  MiniProgramEnum {
    /**
     * 用于分辨
     */
    STATUS_PROCESS("已处理", "after"),
    STATUS_NO_PROCESS("未处理", "before"),
    ;

    /**
     * 说明
     */
    private String meaning;

    /**
     * 代码
     */
    private String code;

    public String getMeaning() {
        return meaning;
    }

    public String getCode() {
        return code;
    }

    MiniProgramEnum(String meaning, String code) {
        this.meaning = meaning;
        this.code = code;
    }
}
