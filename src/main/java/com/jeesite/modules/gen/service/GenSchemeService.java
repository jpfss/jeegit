package com.jeesite.modules.gen.service;

import com.jeesite.common.persistence.Page;
import com.jeesite.common.service.BaseService;
import com.jeesite.common.utils.StringUtils;
import com.jeesite.modules.gen.dao.GenSchemeDao;
import com.jeesite.modules.gen.dao.GenTableColumnDao;
import com.jeesite.modules.gen.dao.GenTableDao;
import com.jeesite.modules.gen.entity.GenConfig;
import com.jeesite.modules.gen.entity.GenScheme;
import com.jeesite.modules.gen.entity.GenTable;
import com.jeesite.modules.gen.entity.GenTableColumn;
import com.jeesite.modules.gen.entity.GenTemplate;
import com.jeesite.modules.gen.util.a;
import com.jeesite.modules.sys.entity.Menu;
import com.jeesite.modules.sys.service.SystemService;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {
	@Autowired
	private GenSchemeDao genSchemeDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	@Autowired
	private SystemService systemService;

	public GenScheme get(String id) {
		/* 50 */ return (GenScheme) this.genSchemeDao.get(id);
	}

	public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		/* 54 */ a.a();
		/* 55 */ genScheme.setPage(page);
		/* 56 */ page.setList(this.genSchemeDao.findList(genScheme));
		/* 57 */ return page;
	}

	@Transactional(readOnly = false)
	public String save(GenScheme genScheme) {
		/* 62 */ if (StringUtils.isBlank(genScheme.getId())) {
			/* 63 */ genScheme.preInsert();
			/* 64 */ this.genSchemeDao.insert(genScheme);
		} else {
			/* 66 */ genScheme.preUpdate();
			/* 67 */ this.genSchemeDao.update(genScheme);
		}
		/* 69 */ return generateCode(genScheme);
	}

	@Transactional(readOnly = false)
	public void createMenu(GenScheme genScheme, Menu topMenu) {
		;
		/* 75 */ String permissionPrefix = StringUtils.lowerCase(genScheme.getModuleName())
				+ (StringUtils.isNotBlank(genScheme.getSubModuleName())
						? ":" + StringUtils.lowerCase(genScheme.getSubModuleName()) : "")
				+ ":" + StringUtils.uncapitalize(genScheme.getGenTable().getClassName());
		/* 76 */ String url = "/" + StringUtils.lowerCase(genScheme.getModuleName())
				+ (StringUtils.isNotBlank(genScheme.getSubModuleName())
						? "/" + StringUtils.lowerCase(genScheme.getSubModuleName()) : "")
				+ "/" + StringUtils.uncapitalize(genScheme.getGenTable().getClassName());

		/* 78 */ topMenu.setName(genScheme.getFunctionName());
		/* 79 */ topMenu.setHref(url);
		/* 80 */ topMenu.setIsShow("1");
		/* 81 */ topMenu.setPermission(permissionPrefix + ":list");
		/* 82 */ this.systemService.saveMenu(topMenu);

		Menu addMenu;
		/* 84 */ (
		/* 85 */ addMenu = new Menu()).setName("增加");
		/* 86 */ addMenu.setIsShow("0");
		/* 87 */ addMenu.setSort(Integer.valueOf(30));
		/* 88 */ addMenu.setPermission(permissionPrefix + ":add");
		/* 89 */ addMenu.setParent(topMenu);
		/* 90 */ this.systemService.saveMenu(addMenu);

		Menu delMenu;
		/* 93 */ (delMenu = new Menu()).setName("删除");
		/* 94 */ delMenu.setIsShow("0");
		/* 95 */ delMenu.setSort(Integer.valueOf(60));
		/* 96 */ delMenu.setPermission(permissionPrefix + ":del");
		/* 97 */ delMenu.setParent(topMenu);
		/* 98 */ this.systemService.saveMenu(delMenu);

		Menu editMenu;
		/* 101 */ (editMenu = new Menu()).setName("编辑");
		/* 102 */ editMenu.setIsShow("0");
		/* 103 */ editMenu.setSort(Integer.valueOf(90));
		/* 104 */ editMenu.setPermission(permissionPrefix + ":edit");
		/* 105 */ editMenu.setParent(topMenu);
		/* 106 */ this.systemService.saveMenu(editMenu);

		Menu viewMenu;
		/* 109 */ (viewMenu = new Menu()).setName("查看");
		/* 110 */ viewMenu.setIsShow("0");
		/* 111 */ viewMenu.setSort(Integer.valueOf(120));
		/* 112 */ viewMenu.setPermission(permissionPrefix + ":view");
		/* 113 */ viewMenu.setParent(topMenu);
		/* 114 */ this.systemService.saveMenu(viewMenu);

		Menu importMenu;
		/* 117 */ (importMenu = new Menu()).setName("导入");
		/* 118 */ importMenu.setIsShow("0");
		/* 119 */ importMenu.setSort(Integer.valueOf(150));
		/* 120 */ importMenu.setPermission(permissionPrefix + ":import");
		/* 121 */ importMenu.setParent(topMenu);
		/* 122 */ this.systemService.saveMenu(importMenu);

		Menu exportMenu;
		/* 125 */ (exportMenu = new Menu()).setName("导出");
		/* 126 */ exportMenu.setIsShow("0");
		/* 127 */ exportMenu.setSort(Integer.valueOf(180));
		/* 128 */ exportMenu.setPermission(permissionPrefix + ":export");
		/* 129 */ exportMenu.setParent(topMenu);
		/* 130 */ this.systemService.saveMenu(exportMenu);
	}

	@Transactional(readOnly = false)
	public void delete(GenScheme genScheme) {
		/* 135 */ this.genSchemeDao.delete(genScheme);
	}

	private String generateCode(GenScheme genScheme) {
		/* 140 */ StringBuilder result = new StringBuilder();

		GenTable genTable;

		/* 144 */ (genTable = (GenTable) this.genTableDao.get(genScheme.getGenTable().getId()))
				.setColumnList(this.genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));

		GenConfig config;

		/* 150 */ List<GenTemplate> templateList = a.a(config = a.b(), genScheme.getCategory(), false);

		List<GenTemplate> childTableTemplateList;

		/* 154 */ if ((childTableTemplateList = a.a(config, genScheme.getCategory(), true)).size() > 0) {
			GenTable parentTable;
			/* 156 */ (parentTable = new GenTable()).setParentTable(genTable.getName());
			/* 157 */ genTable.setChildList(this.genTableDao.findList(parentTable));
		}
		Map<String, Object> childTableModel;
		Iterator localIterator2;
		/* 161 */ for (Iterator localIterator1 = genTable.getChildList().iterator(); localIterator1.hasNext();

		/* 166 */ localIterator2.hasNext()) {
			GenTable childTable;
			/* 162 */ (childTable = (GenTable) localIterator1.next()).setParent(genTable);
			/* 163 */ childTable.setColumnList(
					this.genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
			/* 164 */ genScheme.setGenTable(childTable);
			/* 165 */ childTableModel = a.a(genScheme);
			/* 166 */ localIterator2 = childTableTemplateList.iterator();

			GenTemplate tpl = (GenTemplate) localIterator2.next();
			/* 167 */ result.append(a.a(tpl, childTableModel, true));
			continue;
		}

		/* 172 */ genScheme.setGenTable(genTable);
		/* 173 */ Map<String, Object> model = a.a(genScheme);
		/* 174 */ for (GenTemplate tpl : templateList) {
			/* 175 */ result.append(a.a(tpl, model, true));
		}
		/* 177 */ return result.toString();
	}

	public GenScheme findUniqueByProperty(String propertyName, String value) {
		/* 181 */ return (GenScheme) this.genSchemeDao.findUniqueByProperty(propertyName, value);
	}
}
