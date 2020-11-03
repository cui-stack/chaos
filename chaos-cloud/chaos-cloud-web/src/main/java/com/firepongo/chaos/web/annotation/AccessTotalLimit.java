package com.firepongo.chaos.web.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author G.G
 * @date 2020/4/26 21:34
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessTotalLimit {

    int seconds();
    int maxCount();

}
