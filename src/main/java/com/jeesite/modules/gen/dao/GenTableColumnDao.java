package com.jeesite.modules.gen.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;

@MyBatisDao
public abstract interface GenTableColumnDao extends CrudDao<GenTableColumn> {
	public abstract void deleteByGenTable(GenTable paramGenTable);
}
