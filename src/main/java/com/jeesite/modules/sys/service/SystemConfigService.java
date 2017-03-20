package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.SystemConfig;
import com.jeesite.modules.sys.dao.SystemConfigDao;

/**
 * 系统配置Service
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author www.jeegit.org
 */
@Service
@Transactional(readOnly = true)
public class SystemConfigService extends CrudService<SystemConfigDao, SystemConfig> {

	public SystemConfig get(String id) {
		return super.get(id);
	}
	
	public List<SystemConfig> findList(SystemConfig systemConfig) {
		return super.findList(systemConfig);
	}
	
	public Page<SystemConfig> findPage(Page<SystemConfig> page, SystemConfig systemConfig) {
		return super.findPage(page, systemConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemConfig systemConfig) {
		super.save(systemConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemConfig systemConfig) {
		super.delete(systemConfig);
	}
	
}