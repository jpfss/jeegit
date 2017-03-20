package com.jeesite.modules.gen.dao;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;
import java.util.List;

@MyBatisDao
public abstract interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {
	public abstract List<GenTable> findTableList(GenTable paramGenTable);

	public abstract List<GenTableColumn> findTableColumnList(GenTable paramGenTable);

	public abstract List<String> findTablePK(GenTable paramGenTable);
}
