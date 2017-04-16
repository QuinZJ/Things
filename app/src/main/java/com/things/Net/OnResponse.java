package com.things.Net;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asdf on 2017/4/16.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnResponse {
    enum Type {
        SUCCESS,
        FAIL
    }
    int value();
    Type type() default Type.SUCCESS;
}
