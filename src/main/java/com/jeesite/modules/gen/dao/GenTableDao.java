package com.jeesite.modules.gen.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.gen.entity.GenTable;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public abstract interface GenTableDao
  extends CrudDao<GenTable>
{
  public abstract int buildTable(@Param("sql") String paramString);
}

