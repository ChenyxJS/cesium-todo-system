package com.chenyx.aop.annotation;

import java.lang.annotation.*;

/**
 *
 * 操作日志注解
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    /**
     * 记录操作描述
     */
    String remark() default "";
    /**
     * 是否打日志 默认打
     */
    boolean isLog() default true;
}
