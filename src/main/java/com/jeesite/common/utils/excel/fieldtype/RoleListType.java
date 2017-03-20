package com.jeesite.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.jeesite.common.utils.Collections3;
import com.jeesite.common.utils.SpringContextHolder;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.modules.sys.entity.Role;
import com.jeesite.modules.sys.service.SystemService;

/***
 * 字段类型转换
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
public class RoleListType {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);

	/**
	 * 获取对象值（导入）
	 * 
	 * @param val
	 * @return
	 */
	public static Object getValue(String val) {
		List<Role> roleList = Lists.newArrayList();
		List<Role> allRoleList = systemService.findAllRole();
		for (String s : StringUtils.split(val, ",")) {
			for (Role e : allRoleList) {
				if (StringUtils.trimToEmpty(s).equals(e.getName())) {
					roleList.add(e);
				}
			}
		}
		return roleList.size() > 0 ? roleList : null;
	}

	/**
	 * 设置对象值（导出）
	 * 
	 * @param val
	 * @return
	 */
	public static String setValue(Object val) {
		if (val != null) {
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>) val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}

}
