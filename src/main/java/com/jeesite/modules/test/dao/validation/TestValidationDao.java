package com.jeesite.modules.test.dao.validation;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.validation.TestValidation;

/**
 * 测试校验功能DAO接口
 * @author lgf
 * @version 2016-10-05
 */
@MyBatisDao
public interface TestValidationDao extends CrudDao<TestValidation> {

	
}