package com.jeesite.modules.sys.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.jeesite.modules.sys.service.SystemService;

/**
 * web容器监听器
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月19日
 * @author www.jeegit.org
 */
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()) {
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
