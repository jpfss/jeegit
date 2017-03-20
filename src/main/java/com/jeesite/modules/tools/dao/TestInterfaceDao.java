/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeesite.org/">jeesite</a> All rights reserved.
 */
package com.jeesite.modules.tools.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.tools.entity.TestInterface;

/**
 * 接口DAO接口
 * @author lgf
 * @version 2016-01-07
 */
@MyBatisDao
public interface TestInterfaceDao extends CrudDao<TestInterface> {
	
}