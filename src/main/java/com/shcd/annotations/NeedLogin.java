package com.shcd.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 此注解用于Controller接口方法上，标记为需要登录
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface NeedLogin {

    /**
     * 需要的权限
     */
    int[] value() default {};


}
