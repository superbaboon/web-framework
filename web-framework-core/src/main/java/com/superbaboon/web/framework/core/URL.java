package com.superbaboon.web.framework.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describe metadata of web api(for example: url of the api)
 *
 * Created by junjiewu on 16/4/5.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface URL {

    String url() default "";

}
