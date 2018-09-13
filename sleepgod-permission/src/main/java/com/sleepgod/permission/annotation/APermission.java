package com.sleepgod.permission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APermission {
    int requestCode() default 0;
    String[] permissions();
    String deniedMessage() default "";
    boolean showRationaleDialog() default true;
}
