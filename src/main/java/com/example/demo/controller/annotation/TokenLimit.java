package com.example.demo.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 名称: TokenLimit
 * 描述: 权限注解
 *
 * @author: wqy
 * 创建时间: 2020/6/16 5:11 下午
 * Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenLimit {

    /**
     * 是否检查Token
     * @return 默认检查Token
     */
    boolean CheckToken() default true;
}
