package com.chenyx.entity.enums;

/**
 * <p>
 *  枚举类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
public enum  SystemLogEnum {
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

    SystemLogEnum(String meaning, String code) {
        this.meaning = meaning;
        this.code = code;
    }
}
