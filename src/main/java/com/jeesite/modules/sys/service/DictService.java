package com.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.CrudService;
import com.jeesite.common.utils.CacheUtils;
import com.jeesite.modules.sys.dao.DictDao;
import com.jeesite.modules.sys.entity.Dict;
import com.jeesite.modules.sys.utils.DictUtils;

/**
 * 字典Service
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author  www.jeegit.org
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
