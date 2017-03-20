package com.jeesite.modules.sys.dao;

import java.util.List;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author  www.jeegit.org
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
