package com.firepongo.chaos.web.annotation;

import com.firepongo.chaos.app.constant.RoleConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author G.G
 * @date 2018-07-08 20:40
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ManageLoginToken {
    boolean required() default true;

    String[] roles() default {RoleConstant.GUEST};
}
