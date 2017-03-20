package com.jeesite.common.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeesite.modules.act.entity.Act;

/**
 * Activiti Entity类
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月17日
 * @author www.jeegit.org
 * @param <T>
 *            传入泛型
 */
public abstract class ActEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 流程任务对象
	 */
	protected Act act;

	public ActEntity() {
		super();
	}

	public ActEntity(String id) {
		super(id);
	}

	@JsonIgnore
	public Act getAct() {
		if (act == null) {
			act = new Act();
		}
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

	/**
	 * 获取流程实例ID
	 * 
	 * @return String
	 */
	public String getProcInsId() {
		return this.getAct().getProcInsId();
	}

	/**
	 * 设置流程实例ID
	 * 
	 * @param procInsId
	 */
	public void setProcInsId(String procInsId) {
		this.getAct().setProcInsId(procInsId);
	}
}
