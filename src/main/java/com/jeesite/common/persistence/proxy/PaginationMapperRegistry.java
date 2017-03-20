package com.jeesite.common.persistence.proxy;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

/**
 * 执行代理类注册
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月17日
 * @author www.jeegit.org
 */
public class PaginationMapperRegistry extends MapperRegistry {
	public PaginationMapperRegistry(Configuration config) {
		super(config);
	}

	@Override
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		if (!hasMapper(type)) {
			throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
		}
		try {
			return PaginationMapperProxy.newMapperProxy(type, sqlSession);
		} catch (Exception e) {
			throw new BindingException("Error getting mapper instance. Cause: " + e, e);
		}
	}
}
