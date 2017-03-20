package com.jeesite.modules.gen.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.gen.entity.GenScheme;

@MyBatisDao
public abstract interface GenSchemeDao
  extends CrudDao<GenScheme>
{}

