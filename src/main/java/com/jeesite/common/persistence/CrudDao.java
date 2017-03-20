package com.jeesite.common.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * DAO支持类实现 </br>
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月17日
 * @author www.jeegit.org
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao {

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 *            主键
	 * @return 实体
	 */
	public T get(String id);

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 *            实体对象
	 * @return 实体
	 */
	public T get(T entity);

	/**
	 * 据实体名称、字段名称、字段值获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T findUniqueByProperty(@Param(value = "propertyName") String propertyName,
			@Param(value = "value") Object value);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象</br>
	 * 如：entity.setPage(new Page<T>());
	 * 
	 * @param entity
	 * @return List<T>
	 */
	public List<T> findList(T entity);

	/**
	 * 查询所有数据列表
	 * 
	 * @param entity
	 * @return
	 */
	public List<T> findAllList(T entity);

	/**
	 * 查询所有数据列表
	 * 
	 * @return List<T>
	 */
	public List<T> findAllList();

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return 影响行数
	 */
	public int insert(T entity);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return 影响行数
	 */
	public int update(T entity);

	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String id);

	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * 
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

	/**
	 * 逻辑删除数据</br>
	 * 更新del_flag字段为1,在表包含字段del_flag时，可以调用此方法，将数据隐藏
	 * 
	 * @param id
	 * @return 影响行数
	 */
	public int deleteByLogic(String id);

	/**
	 * 逻辑删除数据</br>
	 * 更新del_flag字段为1,在表包含字段del_flag时，可以调用此方法，将数据隐藏
	 * 
	 * @param entity
	 * @return 影响行数
	 */
	public int deleteByLogic(T entity);

}