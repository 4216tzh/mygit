package com.java.jdkTest.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//设置注解的作用范围
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTypeAnnotation {
	
	String strType();

}
