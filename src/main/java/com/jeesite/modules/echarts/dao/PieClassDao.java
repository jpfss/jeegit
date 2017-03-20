/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeesite.org/">jeesite</a> All rights reserved.
 */
package com.jeesite.modules.echarts.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.echarts.entity.PieClass;

/**
 * 班级DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
@MyBatisDao
public interface PieClassDao extends CrudDao<PieClass> {

}