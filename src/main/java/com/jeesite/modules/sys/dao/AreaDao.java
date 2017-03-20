package com.jeesite.modules.sys.dao;

import com.jeesite.common.persistence.TreeDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Area;

/**
 * 区域DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author www.jeegit.org
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

}
