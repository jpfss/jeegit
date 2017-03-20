package com.jeesite.modules.gen.service;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.BaseService;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.modules.gen.dao.GenDataBaseDictDao;
import com.jeesite.modules.gen.dao.GenTableColumnDao;
import com.jeesite.modules.gen.dao.GenTableDao;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;
import com.jeesite.modules.gen.util.a;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GenTableService extends BaseService {
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private GenDataBaseDictDao genDataBaseDictDao;

	public GenTable get(String id) {
		/* 39 */ GenTable genTable = (GenTable) this.genTableDao.get(id);
		GenTableColumn genTableColumn;
		/* 41 */ (genTableColumn = new GenTableColumn()).setGenTable(new GenTable(genTable.getId()));
		/* 42 */ genTable.setColumnList(this.genTableColumnDao.findList(genTableColumn));
		/* 43 */ return genTable;
	}

	public Page<GenTable> find(Page<GenTable> page, GenTable genTable) {
		/* 47 */ genTable.setPage(page);
		/* 48 */ page.setList(this.genTableDao.findList(genTable));
		/* 49 */ return page;
	}

	public List<GenTable> findAll() {
		/* 53 */ return this.genTableDao.findAllList(new GenTable());
	}

	public List<GenTable> findTableListFormDb(GenTable genTable) {
		/* 62 */ return this.genDataBaseDictDao.findTableList(genTable);
	}

	public boolean checkTableName(String tableName) {
		/* 71 */ if (StringUtils.isBlank(tableName)) {
			/* 72 */ return true;
		}
		GenTable genTable;
		/* 75 */ (genTable = new GenTable()).setName(tableName);

		/* 77 */ return (this.genTableDao.findList(genTable)).size() == 0;
	}

	public boolean checkTableNameFromDB(String tableName) {
		/* 86 */ if (StringUtils.isBlank(tableName)) {
			/* 87 */ return true;
		}
		GenTable genTable;
		/* 90 */ (genTable = new GenTable()).setName(tableName);

		/* 92 */ return (this.genDataBaseDictDao.findTableList(genTable)).size() == 0;
	}

	public GenTable getTableFormDb(GenTable genTable) {
		/* 101 */ if (StringUtils.isNotBlank(genTable.getName())) {
			List<GenTable> list;
			/* 104 */ if ((list = this.genDataBaseDictDao.findTableList(genTable)).size() > 0) {

				/* 107 */ if (StringUtils.isBlank(genTable.getId())) {

					/* 110 */ if (StringUtils.isBlank((genTable = (GenTable) list.get(0)).getComments())) {
						/* 111 */ genTable.setComments(genTable.getName());
					}
					/* 113 */ genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));
				}
				List<GenTableColumn> columnList;
				Iterator localIterator2;
				GenTableColumn e;
				/* 118 */ for (GenTableColumn column : columnList = this.genDataBaseDictDao
						.findTableColumnList(genTable)) {
					/* 119 */ boolean b = false;
					/* 120 */ for (localIterator2 = genTable.getColumnList().iterator(); localIterator2.hasNext();) {
						/* 121 */ if (((e = (GenTableColumn) localIterator2.next()).getName() != null)
								&& (e.getName().equals(column.getName()))) {
							/* 122 */ b = true;
						}
					}
					/* 125 */ if (!b) {
						/* 126 */ genTable.getColumnList().add(column);
					}
				}

				/* 131 */ for (GenTableColumn eg : genTable.getColumnList()) {
					/* 132 */ boolean b = false;
					/* 133 */ for (localIterator2 = columnList.iterator(); localIterator2.hasNext();) {
						/* 134 */ if ((eg = (GenTableColumn) localIterator2.next()).getName().equals(eg.getName())) {
							/* 135 */ b = true;
						}
					}
					/* 138 */ if (!b) {
						/* 139 */ eg.setDelFlag("1");
					}
				}

				/* 144 */ genTable.setPkList(this.genDataBaseDictDao.findTablePK(genTable));

				/* 147 */ a.a(genTable);
			}
		}

		/* 151 */ return genTable;
	}

	@Transactional(readOnly = false)
	public void save(GenTable genTable) {
	
	}

	@Transactional(readOnly = false)
	public void syncSave(GenTable genTable) {
		/* 224 */ genTable.setIsSync("1");
		/* 225 */ this.genTableDao.update(genTable);
	}

	@Transactional(readOnly = false)
	public void saveFromDB(GenTable genTable) {
		/* 231 */ genTable.preInsert();
		/* 232 */ this.genTableDao.insert(genTable);

		/* 234 */ for (Iterator localIterator = genTable.getColumnList().iterator(); localIterator.hasNext();) {
			GenTableColumn column;
			/* 235 */ (column = (GenTableColumn) localIterator.next()).setGenTable(genTable);
			/* 236 */ column.setId(null);
			/* 237 */ column.preInsert();
			/* 238 */ this.genTableColumnDao.insert(column);
		}
	}

	@Transactional(readOnly = false)
	public void delete(GenTable genTable) {
		/* 245 */ this.genTableDao.delete(genTable);
		/* 246 */ this.genTableColumnDao.deleteByGenTable(genTable);
	}

	@Transactional(readOnly = false)
	public void buildTable(String sql) {
		/* 251 */ this.genTableDao.buildTable(sql);
	}
}

/*
 * Location:
 * C:\Users\admin\Desktop\org.jeeframework.gencode-1.5.jar!\com\jeesite\modules\
 * gen\service\GenTableService.class Java compiler version: 6 (50.0) JD-Core
 * Version: 0.7.1
 */