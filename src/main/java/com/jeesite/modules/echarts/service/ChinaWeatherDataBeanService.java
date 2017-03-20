package com.jeesite.modules.echarts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.echarts.entity.ChinaWeatherDataBean;
import com.jeesite.modules.echarts.dao.ChinaWeatherDataBeanDao;

/**
 * 城市气温Service
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author  www.jeegit.org
 */
@Service
@Transactional(readOnly = true)
public class ChinaWeatherDataBeanService extends CrudService<ChinaWeatherDataBeanDao, ChinaWeatherDataBean> {

	public ChinaWeatherDataBean get(String id) {
		return super.get(id);
	}

	public List<ChinaWeatherDataBean> findList(ChinaWeatherDataBean chinaWeatherDataBean) {
		return super.findList(chinaWeatherDataBean);
	}

	public Page<ChinaWeatherDataBean> findPage(Page<ChinaWeatherDataBean> page,
			ChinaWeatherDataBean chinaWeatherDataBean) {
		return super.findPage(page, chinaWeatherDataBean);
	}

	@Transactional(readOnly = false)
	public void save(ChinaWeatherDataBean chinaWeatherDataBean) {
		super.save(chinaWeatherDataBean);
	}

	@Transactional(readOnly = false)
	public void delete(ChinaWeatherDataBean chinaWeatherDataBean) {
		super.delete(chinaWeatherDataBean);
	}

}