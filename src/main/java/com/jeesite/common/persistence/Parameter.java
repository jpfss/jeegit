package com.jeesite.common.persistence;

import java.util.HashMap;

/**
 * 查询参数类
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月17日
 * @author www.jeegit.org
 */
public class Parameter extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造类，例：new Parameter(id, parentIds)</br>
	 *
	 * 
	 * @param values
	 *            可变参数
	 */
	public Parameter(Object... values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				put("p" + (i + 1), values[i]);
			}
		}
	}

	/**
	 * 构造类</br>
	 * 例：new Parameter(new Object[][]{{"id", id}, {"parentIds", parentIds}})
	 * 
	 * @param parameters
	 *            参数二维数组
	 * 
	 */
	public Parameter(Object[][] parameters) {
		if (parameters != null) {
			for (Object[] os : parameters) {
				if (os.length == 2) {
					put((String) os[0], os[1]);
				}
			}
		}
	}

}
