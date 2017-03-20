package com.jeesite.modules.gen.entity;

import com.google.common.collect.Lists;
import com.jeesite.common.persistence.DataEntity;
import com.jeesite.common.utils.StringUtils;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public class GenTableColumn extends DataEntity<GenTableColumn> {
	private static final long serialVersionUID = 1L;
	private GenTable genTable;
	private String name;
	private String comments;
	private String jdbcType;
	private String javaType;
	private String javaField;
	private String isPk;
	private String isInsert;
	private String isEdit;
	private String isForm;
	private String isList;
	private String isQuery;
	private String queryType;
	private String showType;
	private String dictType;
	private Integer sort;
	private String tableName;
	private String fieldLabels;
	private String fieldKeys;
	private String searchLabel;
	private String searchKey;
	private String isNull;
	private String validateType;
	private String minLength;
	private String maxLength;
	private String minValue;
	private String maxValue;

	public GenTableColumn() {
	}

	public GenTableColumn(String id) {
		/* 58 */ super(id);
	}

	public GenTableColumn(GenTable genTable) {
		/* 62 */ this.genTable = genTable;
	}

	public GenTable getGenTable() {
		/* 66 */ return this.genTable;
	}

	public void setGenTable(GenTable genTable) {
		/* 70 */ this.genTable = genTable;
	}

	@Length(min = 1, max = 200)
	public String getName() {
		/* 75 */ return StringUtils.lowerCase(this.name);
	}

	public void setName(String name) {
		/* 79 */ this.name = name;
	}

	public String getComments() {
		/* 83 */ return this.comments;
	}

	public void setComments(String comments) {
		/* 87 */ this.comments = comments;
	}

	public String getJdbcType() {
		/* 91 */ return StringUtils.lowerCase(this.jdbcType);
	}

	public void setJdbcType(String jdbcType) {
		/* 95 */ this.jdbcType = jdbcType;
	}

	public String getJavaType() {
		/* 99 */ return this.javaType;
	}

	public void setJavaType(String javaType) {
		/* 103 */ this.javaType = javaType;
	}

	public String getJavaField() {
		/* 107 */ return this.javaField;
	}

	public void setJavaField(String javaField) {
		/* 111 */ this.javaField = javaField;
	}

	public String getIsPk() {
		/* 115 */ return this.isPk;
	}

	public void setIsPk(String isPk) {
		/* 119 */ this.isPk = isPk;
	}

	public String getIsNull() {
		/* 123 */ return this.isNull;
	}

	public void setIsNull(String isNull) {
		/* 127 */ this.isNull = isNull;
	}

	public String getIsInsert() {
		/* 131 */ return this.isInsert;
	}

	public void setIsInsert(String isInsert) {
		/* 135 */ this.isInsert = isInsert;
	}

	public String getIsEdit() {
		/* 139 */ return this.isEdit;
	}

	public void setIsEdit(String isEdit) {
		/* 143 */ this.isEdit = isEdit;
	}

	public void setIsForm(String isForm) {
		/* 147 */ this.isForm = isForm;
	}

	public String getIsForm() {
		/* 151 */ return this.isForm;
	}

	public String getIsList() {
		/* 155 */ return this.isList;
	}

	public void setIsList(String isList) {
		/* 159 */ this.isList = isList;
	}

	public String getIsQuery() {
		/* 163 */ return this.isQuery;
	}

	public void setIsQuery(String isQuery) {
		/* 167 */ this.isQuery = isQuery;
	}

	public String getQueryType() {
		/* 171 */ return this.queryType;
	}

	public void setQueryType(String queryType) {
		/* 175 */ this.queryType = queryType;
	}

	public String getShowType() {
		/* 179 */ return this.showType;
	}

	public void setShowType(String showType) {
		/* 183 */ this.showType = showType;
	}

	public String getDictType() {
		/* 187 */ return this.dictType == null ? "" : this.dictType;
	}

	public void setDictType(String dictType) {
		/* 191 */ this.dictType = dictType;
	}

	public Integer getSort() {
		/* 195 */ return this.sort;
	}

	public void setSort(Integer sort) {
		/* 199 */ this.sort = sort;
	}

	public String getNameAndComments() {
		/* 207 */ return getName()
				+ (this.comments == null ? "" : new StringBuilder("  :  ").append(this.comments).toString());
	}

	public String getDataLength() {
		/* 215 */ String[] ss = StringUtils.split(StringUtils.substringBetween(getJdbcType(), "(", ")"), ",");
		/* 216 */ if ((ss != null) && (ss.length == 1)) {
			/* 217 */ return ss[0];
		}
		/* 219 */ return "0";
	}

	public String getSimpleJavaType() {
		/* 227 */ if ("This".equals(getJavaType())) {
			/* 228 */ return StringUtils.capitalize(this.genTable.getClassName());
		}
		/* 230 */ return StringUtils.indexOf(getJavaType(), ".") != -1 ?
				/* 231 */ StringUtils.substringAfterLast(getJavaType(), ".")
				:
				/* 232 */ getJavaType();
	}

	public String getSimpleJavaField() {
		/* 240 */ return StringUtils.substringBefore(getJavaField(), ".");
	}

	public String getJavaFieldId() {
		/* 248 */ return StringUtils.substringBefore(getJavaField(), "|");
	}

	public String getJavaFieldName() {
		/* 256 */ String[][] ss = getJavaFieldAttrs();
		/* 257 */ return ss.length > 0 ? getSimpleJavaField() + "." + ss[0][0] : "";
	}

	public String[][] getJavaFieldAttrs() {
		/* 265 */ String[] ss = StringUtils.split(StringUtils.substringAfter(getJavaField(), "|"), "|");
		/* 266 */ String[][] sss = new String[ss.length][2];
		/* 267 */ if (ss != null) {
			/* 268 */ for (int i = 0; i < ss.length; i++) {
				/* 269 */ sss[i][0] = ss[i];
				/* 270 */ sss[i][1] = StringUtils.toUnderScoreCase(ss[i]);
			}
		}
		/* 273 */ return sss;
	}

	public List<String> getAnnotationList() {
		/* 281 */ List<String> list = (List) Lists.newArrayList();

		/* 283 */ if ("This".equals(getJavaType())) {
			/* 284 */ list.add("com.fasterxml.jackson.annotation.JsonBackReference");
		}
		/* 286 */ if ("java.util.Date".equals(getJavaType())) {
			/* 287 */ list.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
		}

		/* 290 */ if ((!"1".equals(getIsNull())) && (!"String".equals(getJavaType()))) {
			/* 291 */ list.add("javax.validation.constraints.NotNull(message=\"" + getComments() + "不能为空\")");
		}
		/* 293 */ else if ((!"1".equals(getIsNull())) && ("String".equals(getJavaType())) &&
				/* 294 */ (this.minLength != null) && (!this.minLength.equals(""))) {
			/* 295 */ list.add(
					"org.hibernate.validator.constraints.Length(min=" + this.minLength + ", max=" + this.maxLength +
							/* 296 */ ", message=\"" + getComments() + "长度必须介于 " + this.minLength + " 和 "
							+ this.maxLength + " 之间\")");
		}

		/* 301 */ if ("email".equals(this.validateType)) {
			/* 302 */ list.add("org.hibernate.validator.constraints.Email(message=\"" + getComments() + "必须为合法邮箱\")");
		}
		/* 304 */ if ("url".equals(this.validateType)) {
			/* 305 */ list.add("org.hibernate.validator.constraints.URL(message=\"" + getComments() + "必须为合法网址\")");
		}
		/* 307 */ if ("creditcard".equals(this.validateType)) {
			/* 308 */ list.add(
					"org.hibernate.validator.constraints.CreditCardNumber(message=\"" + getComments() + "必须为合法信用卡号\")");
		}
		/* 310 */ if (("number".equals(this.validateType)) || ("digits".equals(this.validateType))) {
			/* 311 */ if ((this.minValue != null) && (!this.minValue.equals(""))) {
				/* 312 */ if (this.minValue.contains(".")) {
					/* 313 */ String minv = this.minValue.replace(".", "_digitalPoint_");
					/* 314 */ list.add("javax.validation.constraints.Min(value=(long)" + minv + ",message=\""
							+ getComments() + "的最小值不能小于" + minv + "\")");
				} else {
					/* 316 */ list.add("javax.validation.constraints.Min(value=" + this.minValue + ",message=\""
							+ getComments() + "的最小值不能小于" + this.minValue + "\")");
				}
			}

			/* 320 */ if ((this.maxValue != null) && (!this.maxValue.equals(""))) {
				/* 321 */ if (this.maxValue.contains(".")) {
					/* 322 */ String maxv = this.maxValue.replace(".", "_digitalPoint_");
					/* 323 */ list.add("javax.validation.constraints.Max(value=(long)" + maxv + ",message=\""
							+ getComments() + "的最大值不能超过" + maxv + "\")");
				} else {
					/* 325 */ list.add("javax.validation.constraints.Max(value=" + this.maxValue + ",message=\""
							+ getComments() + "的最大值不能超过" + this.maxValue + "\")");
				}
			}
		}

		/* 333 */ return list;
	}

	public List<String> getSimpleAnnotationList() {
		/* 341 */ List<String> list = (List) Lists.newArrayList();
		/* 342 */ for (String ann : getAnnotationList()) {
			/* 343 */ String anno = StringUtils.substringAfterLast(ann, ".");
			/* 344 */ anno = anno.replace("_digitalPoint_", ".");
			/* 345 */ list.add(anno);
		}
		/* 347 */ return list;
	}

	public Boolean getIsNotBaseField() {
		/* 355 */ if ((!StringUtils.equals(getSimpleJavaField(), "id")) &&
				/* 356 */ (!StringUtils.equals(getSimpleJavaField(), "remarks")) &&
				/* 357 */ (!StringUtils.equals(getSimpleJavaField(), "createBy")) &&
				/* 358 */ (!StringUtils.equals(getSimpleJavaField(), "createDate")) &&
				/* 359 */ (!StringUtils.equals(getSimpleJavaField(), "updateBy")) &&
				/* 360 */ (!StringUtils.equals(getSimpleJavaField(), "updateDate")) &&
				/* 361 */ (!StringUtils.equals(getSimpleJavaField(), "delFlag")))
			return Boolean.valueOf(true);
		/* 355 */ return

		/* 361 */ Boolean.valueOf(false);
	}

	public void setTableName(String tableName) {
		/* 365 */ this.tableName = tableName;
	}

	public String getTableName() {
		/* 369 */ return this.tableName;
	}

	public void setFieldLabels(String fieldLabels) {
		/* 373 */ this.fieldLabels = fieldLabels;
	}

	public String getFieldLabels() {
		/* 377 */ return this.fieldLabels;
	}

	public void setFieldKeys(String fieldKeys) {
		/* 381 */ this.fieldKeys = fieldKeys;
	}

	public String getFieldKeys() {
		/* 385 */ return this.fieldKeys;
	}

	public void setSearchLabel(String searchLabel) {
		/* 389 */ this.searchLabel = searchLabel;
	}

	public String getSearchLabel() {
		/* 393 */ return this.searchLabel;
	}

	public void setSearchKey(String searchKey) {
		/* 397 */ this.searchKey = searchKey;
	}

	public String getSearchKey() {
		/* 401 */ return this.searchKey;
	}

	public void setMinLength(String minLength) {
		/* 408 */ this.minLength = minLength;
	}

	public String getMinLength() {
		/* 415 */ return this.minLength;
	}

	public void setValidateType(String validateType) {
		/* 422 */ this.validateType = validateType;
	}

	public String getValidateType() {
		/* 429 */ return this.validateType;
	}

	public void setMaxLength(String maxLength) {
		/* 436 */ this.maxLength = maxLength;
	}

	public String getMaxLength() {
		/* 443 */ return this.maxLength;
	}

	public void setMinValue(String minValue) {
		/* 450 */ this.minValue = minValue;
	}

	public String getMinValue() {
		/* 457 */ return this.minValue;
	}

	public void setMaxValue(String maxValue) {
		/* 464 */ this.maxValue = maxValue;
	}

	public String getMaxValue() {
		/* 471 */ return this.maxValue;
	}
}

