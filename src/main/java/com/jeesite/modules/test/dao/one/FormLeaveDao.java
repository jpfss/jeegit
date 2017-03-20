package com.jeesite.modules.test.dao.one;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.one.FormLeave;

/**
 * 请假表单DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author www.jeegit.org
 */
@MyBatisDao
public interface FormLeaveDao extends CrudDao<FormLeave> {

}