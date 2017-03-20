package com.jeesite.modules.echarts.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.echarts.entity.PieClass;
import com.jeesite.modules.echarts.dao.PieClassDao;

/**
 * 班级Service
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
@Service
@Transactional(readOnly = true)
public class PieClassService extends CrudService<PieClassDao, PieClass> {

	public PieClass get(String id) {
		return super.get(id);
	}

	public List<PieClass> findList(PieClass pieClass) {
		return super.findList(pieClass);
	}

	public Page<PieClass> findPage(Page<PieClass> page, PieClass pieClass) {
		return super.findPage(page, pieClass);
	}

	@Transactional(readOnly = false)
	public void save(PieClass pieClass) {
		super.save(pieClass);
	}

	@Transactional(readOnly = false)
	public void delete(PieClass pieClass) {
		super.delete(pieClass);
	}

}