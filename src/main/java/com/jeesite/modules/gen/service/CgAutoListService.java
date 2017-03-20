package com.jeesite.modules.gen.service;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.BaseService;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.modules.gen.dao.GenDataBaseDictDao;
import com.jeesite.modules.gen.dao.GenTableColumnDao;
import com.jeesite.modules.gen.dao.GenTableDao;
import com.jeesite.modules.gen.entity.GenScheme;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CgAutoListService extends BaseService {
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;

	public GenTable get(String id) {
		/* 43 */ GenTable genTable = (GenTable) this.genTableDao.get(id);
		GenTableColumn genTableColumn;
		/* 45 */ (genTableColumn = new GenTableColumn()).setGenTable(new GenTable(genTable.getId()));
		/* 46 */ genTable.setColumnList(this.genTableColumnDao.findList(genTableColumn));
		/* 47 */ return genTable;
	}

	public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
		/* 51 */ genTable.setPage(page);
		/* 52 */ page.setList(this.genTableDao.findList(genTable));
		/* 53 */ return page;
	}

	public List<GenTable> findAll() {
		/* 57 */ return this.genTableDao.findAllList(new GenTable());
	}

	public List<GenTable> findTableListFormDb(GenTable genTable) {
		/* 66 */ return this.genDataBaseDictDao.findTableList(genTable);
	}

	public boolean checkTableName(String tableName) {
		/* 75 */ if (StringUtils.isBlank(tableName)) {
			/* 76 */ return true;
		}
		GenTable genTable;
		/* 79 */ (genTable = new GenTable()).setName(tableName);

		/* 81 */ return (this.genTableDao.findList(genTable)).size() == 0;
	}

	public boolean checkTableNameFromDB(String tableName) {
		/* 90 */ if (StringUtils.isBlank(tableName)) {
			/* 91 */ return true;
		}
		GenTable genTable;
		/* 94 */ (genTable = new GenTable()).setName(tableName);

		/* 96 */ return (this.genDataBaseDictDao.findTableList(genTable)).size() == 0;
	}

	public String generateCode(GenScheme genScheme) {

		return "";
	}

	public String generateListCode(GenScheme genScheme) {
		return "";

	}
}
