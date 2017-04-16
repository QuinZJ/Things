package com.things.Bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asdf on 2017/4/16.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HTMLToken {
    String SELF = "SELF";
    enum Type {
        TEXT,
        ATTR
    }
    String elem() default SELF;
    Type type() default Type.TEXT;
    String attr() default "";
}
