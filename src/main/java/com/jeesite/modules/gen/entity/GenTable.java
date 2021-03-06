package com.jeesite.modules.gen.entity;

import com.google.common.collect.Lists;
import com.jeesite.common.persistence.DataEntity;
import com.jeesite.common.utils.StringUtils;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public class GenTable extends DataEntity<GenTable> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String comments;
	private String tableType;
	private String className;
	private String parentTable;
	private String parentTableFk;
	private String isSync;
	private List<GenTableColumn> columnList = (List) Lists.newArrayList();

	private String nameLike;

	private List<String> pkList;

	private GenTable parent;
	private List<GenTable> childList = (List) Lists.newArrayList();

	public GenTable() {
	}

	public GenTable(String id) {
		super(id);
	}

	@Length(min = 1, max = 200)
	public String getName() {
		return StringUtils.lowerCase(this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentTable() {
		return StringUtils.lowerCase(this.parentTable);
	}

	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}

	public String getParentTableFk() {
		return StringUtils.lowerCase(this.parentTableFk);
	}

	public void setParentTableFk(String parentTableFk) {
		this.parentTableFk = parentTableFk;
	}

	public List<String> getPkList() {
		return this.pkList;
	}

	public void setPkList(List<String> pkList) {
		this.pkList = pkList;
	}

	public String getNameLike() {
		return this.nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public GenTable getParent() {
		return this.parent;
	}

	public void setParent(GenTable parent) {
		this.parent = parent;
	}

	public List<GenTableColumn> getColumnList() {
		return this.columnList;
	}

	public void setColumnList(List<GenTableColumn> columnList) {
		this.columnList = columnList;
	}

	public List<GenTable> getChildList() {
		return this.childList;
	}

	public void setChildList(List<GenTable> childList) {
		this.childList = childList;
	}

	public String getNameAndComments() {
		/* 133 */ return getName()
				+ (this.comments == null ? "" : new StringBuilder("  :  ").append(this.comments).toString());
	}

	public List<String> getImportList() {
		/* 141 */ List<String> importList = (List) Lists.newArrayList();
		/* 142 */ for (GenTableColumn column : getColumnList()) {
			/* 143 */ if ((column.getIsNotBaseField().booleanValue())
					|| (("1".equals(column.getIsQuery())) && ("between".equals(column.getQueryType())) && (
					/* 144 */ ("createDate".equals(column.getSimpleJavaField()))
							|| ("updateDate".equals(column.getSimpleJavaField()))))) {
				/* 146 */ if ((StringUtils.indexOf(column.getJavaType(), ".") != -1)
						&& (!importList.contains(column.getJavaType()))) {
					/* 147 */ importList.add(column.getJavaType());
				}
			}
			/* 150 */ if (column.getIsNotBaseField().booleanValue()) {
				/* 152 */ for (String ann : column.getAnnotationList()) {
					/* 153 */ if (!importList.contains(StringUtils.substringBeforeLast(ann, "("))) {
						/* 154 */ importList.add(StringUtils.substringBefore(ann, "("));
					}
				}
			}
		}

		/* 160 */ if ((getChildList() != null) && (getChildList().size() > 0)) {
			/* 161 */ if (!importList.contains("java.util.List")) {
				/* 162 */ importList.add("java.util.List");
			}
			/* 164 */ if (!importList.contains("com.google.common.collect.Lists")) {
				/* 165 */ importList.add("com.google.common.collect.Lists");
			}
		}
		/* 168 */ return importList;
	}

	public List<String> getImportGridJavaList() {
		/* 177 */ List<String> importList = (List) Lists.newArrayList();
		/* 178 */ for (GenTableColumn column : getColumnList()) {
			/* 179 */ if ((column.getTableName() != null) && (!column.getTableName().equals(""))) {
				/* 181 */ if ((StringUtils.indexOf(column.getJavaType(), ".") != -1)
						&& (!importList.contains(column.getJavaType()))) {
					/* 182 */ importList.add(column.getJavaType());
				}
			}
		}
		/* 186 */ return importList;
	}

	public List<String> getImportGridJavaDaoList() {
		/* 190 */ boolean isNeedList = false;
		/* 191 */ List<String> importList = (List) Lists.newArrayList();
		/* 192 */ for (GenTableColumn column : getColumnList()) {
			/* 193 */ if ((column.getTableName() != null) && (!column.getTableName().equals(""))) {
				/* 195 */ if ((StringUtils.indexOf(column.getJavaType(), ".") != -1)
						&& (!importList.contains(column.getJavaType()))) {
					/* 196 */ importList.add(column.getJavaType());
					/* 197 */ isNeedList = true;
				}
			}
		}
		/* 201 */ if ((isNeedList) &&
				/* 202 */ (!importList.contains("java.util.List"))) {
			/* 203 */ importList.add("java.util.List");
		}

		/* 206 */ return importList;
	}

	public Boolean getParentExists() {
		/* 213 */ if ((this.parent != null) && (StringUtils.isNotBlank(this.parentTable))
				&& (StringUtils.isNotBlank(this.parentTableFk)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	public Boolean getCreateDateExists() {
		/* 221 */ for (GenTableColumn c : this.columnList) {
			/* 222 */ if ("create_date".equals(c.getName())) {
				/* 223 */ return Boolean.valueOf(true);
			}
		}
		/* 226 */ return Boolean.valueOf(false);
	}

	public Boolean getUpdateDateExists() {
		/* 234 */ for (GenTableColumn c : this.columnList) {
			/* 235 */ if ("update_date".equals(c.getName())) {
				/* 236 */ return Boolean.valueOf(true);
			}
		}
		/* 239 */ return Boolean.valueOf(false);
	}

	public Boolean getDelFlagExists() {
		for (GenTableColumn c : this.columnList) {
			if ("del_flag".equals(c.getName())) {
				return Boolean.valueOf(true);
			}
		}
		return Boolean.valueOf(false);
	}

	public void setIsSync(String isSync) {
		this.isSync = isSync;
	}

	public String getIsSync() {
		return this.isSync;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getTableType() {
		return this.tableType;
	}
}
