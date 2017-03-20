package com.jeesite.modules.test.dao.grid;

import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.grid.Category;

/**
 * 商品分类DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author www.jeegit.org
 */
@MyBatisDao
public interface CategoryDao extends CrudDao<Category> {

}