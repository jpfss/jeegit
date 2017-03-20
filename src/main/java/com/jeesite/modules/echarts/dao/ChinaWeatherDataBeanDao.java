package com.jeesite.modules.echarts.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.echarts.entity.ChinaWeatherDataBean;

/**
 * 城市气温DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
@MyBatisDao
public interface ChinaWeatherDataBeanDao extends CrudDao<ChinaWeatherDataBean> {

}