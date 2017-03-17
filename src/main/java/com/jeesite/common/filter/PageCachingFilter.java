package com.jeesite.common.filter;

import com.jeesite.common.utils.CacheUtils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

/**
 * 页面高速缓存过滤器
 *
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
public class PageCachingFilter extends SimplePageCachingFilter {

	/**
	 * 重写SimplePageCachingFilter 中的getCacheManager方法。
	 */
	@Override
	protected CacheManager getCacheManager() {
		return CacheUtils.getCacheManager();
	}

}
