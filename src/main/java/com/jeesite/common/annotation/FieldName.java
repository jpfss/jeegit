package com.jeesite.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * bean注解，用于程序在运行时注入相关的字段
 *
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldName {

	/**
	 *  
	 * 
	 * @return 注解名
	 */
	String value();

}
