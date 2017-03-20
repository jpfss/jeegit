package com.jeesite.modules.test.dao.onetomany;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.onetomany.TestDataChild;

/**
 * 票务代理DAO接口
 * @author liugf
 * @version 2016-10-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {

	
}