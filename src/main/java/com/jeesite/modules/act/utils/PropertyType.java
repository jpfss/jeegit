package com.jeesite.modules.act.utils;

import java.util.Date;

/**
 * 属性数据类型
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
public enum PropertyType {

	S(String.class), I(Integer.class), L(Long.class), F(Float.class), N(Double.class), D(Date.class), SD(
			java.sql.Date.class), B(Boolean.class);

	private Class<?> clazz;

	private PropertyType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}
}