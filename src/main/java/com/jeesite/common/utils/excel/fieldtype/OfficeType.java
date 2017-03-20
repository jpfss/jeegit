package com.jeesite.common.utils.excel.fieldtype;

import com.jeesite.common.utils.StringUtils;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
public class OfficeType {

	/**
	 * 获取对象值（导入）
	 * 
	 * @param val
	 * @return
	 */
	public static Object getValue(String val) {
		for (Office e : UserUtils.getOfficeList()) {
			if (StringUtils.trimToEmpty(val).equals(e.getName())) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 设置对象值（导出）
	 * 
	 * @param val
	 * @return
	 */
	public static String setValue(Object val) {
		if (val != null && ((Office) val).getName() != null) {
			return ((Office) val).getName();
		}
		return "";
	}
}
