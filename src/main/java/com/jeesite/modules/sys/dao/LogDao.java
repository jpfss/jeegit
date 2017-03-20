package com.jeesite.modules.sys.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Log;

/*
 *  日志DAO接口
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

	public void empty();
}
