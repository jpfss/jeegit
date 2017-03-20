package com.jeesite.modules.oa.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.oa.entity.TestAudit;

/**
 * 审批DAO接口
 * 
 * @author jeesite
 * @version 2014-05-16
 */
@MyBatisDao
public interface TestAuditDao extends CrudDao<TestAudit> {

	public TestAudit getByProcInsId(String procInsId);

	public int updateInsId(TestAudit testAudit);

	public int updateHrText(TestAudit testAudit);

	public int updateLeadText(TestAudit testAudit);

	public int updateMainLeadText(TestAudit testAudit);

}
