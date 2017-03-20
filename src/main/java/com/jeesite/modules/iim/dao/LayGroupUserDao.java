package com.jeesite.modules.iim.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.iim.entity.LayGroupUser;

/**
 * 群组DAO接口
 * @author lgf
 * @version 2016-08-07
 */
@MyBatisDao
public interface LayGroupUserDao extends CrudDao<LayGroupUser> {

	
}