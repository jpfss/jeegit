package com.jeesite.modules.gen.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.gen.entity.GenTemplate;

@MyBatisDao
public abstract interface GenTemplateDao extends CrudDao<GenTemplate> {
}
