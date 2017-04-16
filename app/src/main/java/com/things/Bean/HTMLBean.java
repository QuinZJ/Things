package com.things.Bean;

import java.lang.reflect.Field;

/**
 * Created by asdf on 2017/4/16.
 */

public class HTMLBean {
    public String toString() {
        StringBuilder builder = new StringBuilder().append(this.getClass().getSimpleName()).append('@').append(this.hashCode());
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                builder.append(field.getName()).append(':').append(String.valueOf(field.get(this))).append('#');
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
