package com.jeesite.modules.sys.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.SystemConfig;

/**
 *  系统配置DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author  www.jeegit.org
 */
@MyBatisDao
public interface SystemConfigDao extends CrudDao<SystemConfig> {
	
}