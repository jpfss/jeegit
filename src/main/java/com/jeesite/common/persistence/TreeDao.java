package com.jeesite.common.persistence;

import java.util.List;

/**
 * TreeDao支持类实现</br>
 * 
 * 树形结构类实现函数
 * 
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月17日
 * @author www.jeegit.org
 * @param <T>
 */
public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {

	/**
	 * 找到所有子节点
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * 
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);

}