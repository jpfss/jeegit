package com.jeesite.modules.gen.entity;

import com.google.common.collect.Lists;
import com.jeesite.common.persistence.DataEntity;
import com.jeesite.common.utils.StringUtils;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Length;

@XmlRootElement(name = "template")
public class GenTemplate extends DataEntity<GenTemplate> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String category;
	private String filePath;
	private String fileName;
	private String content;

	public GenTemplate() {
	}

	public GenTemplate(String id) {
		/* 37 */ super(id);
	}

	@Length(min = 1, max = 200)
	public String getName() {
		/* 42 */ return this.name;
	}

	public void setName(String name) {
		/* 46 */ this.name = name;
	}

	public String getFileName() {
		/* 50 */ return this.fileName;
	}

	public void setFileName(String fileName) {
		/* 54 */ this.fileName = fileName;
	}

	public String getFilePath() {
		/* 58 */ return this.filePath;
	}

	public void setFilePath(String filePath) {
		/* 62 */ this.filePath = filePath;
	}

	public String getContent() {
		/* 66 */ return this.content;
	}

	public void setContent(String content) {
		/* 70 */ this.content = content;
	}

	public String getCategory() {
		/* 74 */ return this.category;
	}

	public void setCategory(String category) {
		/* 78 */ this.category = category;
	}

	@XmlTransient
	public List<String> getCategoryList() {
		/* 83 */ if (this.category == null) {
			/* 84 */ return (List) Lists.newArrayList();
		}
		/* 86 */ return (List) Lists.newArrayList(StringUtils.split(this.category, ","));
	}

	public void setCategoryList(List<String> categoryList) {
		/* 91 */ if (categoryList == null) {
			/* 92 */ this.category = "";
		} else {
			/* 94 */ this.category = ("," + StringUtils.join(categoryList, ",") + ",");
		}
	}
}
