package com.jeesite.modules.test.dao.tree;

import com.jeesite.common.persistence.TreeDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.tree.TestTree;

/**
 * 组织机构DAO接口
 * @author liugf
 * @version 2016-10-04
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}