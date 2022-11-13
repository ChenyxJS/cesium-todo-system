package com.chenyx.entity.enums;

/**
 *  枚举类
 *
 * @author chenyx
 * @since 2022-11-13
*/
public enum  TodoEnum {
    /**
     * 用于分辨
     */
    STATUS_PROCESS("已处理", "after"),
    STATUS_NO_PROCESS("未处理", "before"),
    ;

    /**
     * 说明
     */
    private final String meaning;

    /**
     * 代码
     */
    private final String code;

    public String getMeaning() {
        return meaning;
    }

    public String getCode() {
        return code;
    }

    TodoEnum(String meaning, String code) {
        this.meaning = meaning;
        this.code = code;
    }
}