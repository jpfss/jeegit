package com.jeesite.modules.test.dao.grid;

import com.jeesite.modules.test.entity.grid.Category;
import java.util.List;
import com.jeesite.common.persistence.CrudDao;
import com.jeesite.common.persistence.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.grid.Goods;

/**
 * 商品DAO接口
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author www.jeegit.org
 */
@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {

	public List<Category> findListBycategory(Category category);

}