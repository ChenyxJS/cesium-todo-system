package com.chenyx.entity.enums;

/**
 * 用户信息 枚举类
 *
 * @author 空城
 * @since 2021-07-29
*/
public enum UserInfoStatusEnum {
    /**
     * 用于分辨
     */
    STATUS_cancellation("已注销", -1),
    STATUS_NORMAL("已注册",1),
    STATUS_UNREGISTERED("未注册",0),
    ;

    /**
     * 说明
     */
    private final String meaning;

    /**
     * 代码
     */
    private final Integer code;

    public String getMeaning() {
        return meaning;
    }

    public Integer getCode() {
        return code;
    }

    UserInfoStatusEnum(String meaning, Integer code) {
        this.meaning = meaning;
        this.code = code;
    }
}
