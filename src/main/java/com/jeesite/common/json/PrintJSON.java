package com.jeesite.common.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 打印json 数据
 * 
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
public class PrintJSON {
	private static Logger logger = LoggerFactory.getLogger(PrintJSON.class);

	public static void write(HttpServletResponse response, String content) {
		response.reset();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(content);
			/**
			 * java8语言新特性，如果出现异常，将自动释放对象。
			 */
			pw.flush();

		} catch (IOException e) {
			logger.error("PrintJSON-write", e);
		}
	}

}
