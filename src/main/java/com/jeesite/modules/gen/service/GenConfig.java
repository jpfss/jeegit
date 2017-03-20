package com.jeesite.modules.gen.entity;

import com.jeesite.modules.sys.entity.Dict;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
public class GenConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<GenCategory> categoryList;
	private List<Dict> javaTypeList;
	private List<Dict> queryTypeList;
	private List<Dict> showTypeList;
	private List<Dict> validateTypeList;

	@XmlElementWrapper(name = "validateType")
	@XmlElement(name = "dict")
	public List<Dict> getValidateTypeList() {
		/* 33 */ return this.validateTypeList;
	}

	public void setValidateTypeList(List<Dict> validateTypeList) {
		/* 37 */ this.validateTypeList = validateTypeList;
	}

	@XmlElementWrapper(name = "category")
	@XmlElement(name = "category")
	public List<GenCategory> getCategoryList() {
		/* 47 */ return this.categoryList;
	}

	public void setCategoryList(List<GenCategory> categoryList) {
		/* 51 */ this.categoryList = categoryList;
	}

	@XmlElementWrapper(name = "javaType")
	@XmlElement(name = "dict")
	public List<Dict> getJavaTypeList() {
		return this.javaTypeList;
	}

	public void setJavaTypeList(List<Dict> javaTypeList) {
		this.javaTypeList = javaTypeList;
	}

	@XmlElementWrapper(name = "queryType")
	@XmlElement(name = "dict")
	public List<Dict> getQueryTypeList() {
		return this.queryTypeList;
	}

	public void setQueryTypeList(List<Dict> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}

	@XmlElementWrapper(name = "showType")
	@XmlElement(name = "dict")
	public List<Dict> getShowTypeList() {
		return this.showTypeList;
	}

	public void setShowTypeList(List<Dict> showTypeList) {
		this.showTypeList = showTypeList;
	}
}
