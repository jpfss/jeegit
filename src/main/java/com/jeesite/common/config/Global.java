package com.jeesite.common.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

import org.springframework.core.io.DefaultResourceLoader;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import com.jeesite.common.utils.PropertiesLoader;
import com.jeesite.common.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局配置类
 *
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
public class Global {

	private static Logger logger = LoggerFactory.getLogger(Global.class);

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("jeesite.properties");

	/**
	 * 显示 1/隐藏0
	 */
	public static final String SHOW = "1";
	/**
	 * 显示 1/隐藏0
	 */
	public static final String HIDE = "0";

	/**
	 * 是1/否0
	 */
	public static final String YES = "1";
	/**
	 * 是/否
	 */
	public static final String NO = "0";

	/**
	 * 对true/错false
	 */
	public static final String TRUE = "true";
	/**
	 * 对true/错false
	 */
	public static final String FALSE = "false";

	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";

	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}

	/**
	 * 获取配置
	 * 
	 * 
	 * @param key
	 *            配置文件属性名
	 * @return 配置文件属性值
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * 获取后台根路径
	 * 
	 * @return 管理路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}

	/**
	 * 获取前台根路径
	 * 
	 * @return 前台默认路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}

	/**
	 * 获取URL后缀</br>
	 * 例如：localhost:8080/a.html
	 * 
	 * @return 请求的后缀名
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}

	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 * 
	 * @return 是否
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}

	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 * 
	 * @return 是否
	 */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}

	/**
	 * 页面获取常量
	 * 
	 * @see ${fns:getConst('YES')}
	 * @param field
	 * @return 常量对象
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			logger.error("getConst页面获取常量{}出现异常:", field, e);
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 * 
	 * @return 路径字符串
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)) {
			try {
				dir = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				logger.error("getUserfilesBaseDir", e);
			}
		}
		if (!dir.endsWith("/")) {
			dir += "/";
		}

		return dir;
	}

	/**
	 * 获取工程路径
	 * 
	 * @return
	 */
	public static String getProjectPath() {
		// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)) {
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null) {
				while (true) {
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()) {
						break;
					}
					if (file.getParentFile() != null) {
						file = file.getParentFile();
					} else {
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			logger.error("getProjectPath", e);
		}
		return projectPath;
	}

	/**
	 * 写入properties信息
	 * 
	 * @param key
	 *            键值名
	 * @param value
	 *            键值
	 */
	public static void modifyConfig(String key, String value) {
		try {
			// 从输入流中读取属性列表（键和元素对）
			Properties prop = getProperties();
			prop.setProperty(key, value);
			String path = Global.class.getResource("/jeesite.properties").getPath();
			FileOutputStream outputFile = new FileOutputStream(path);
			prop.store(outputFile, "modify");
			outputFile.close();
			outputFile.flush();
		} catch (Exception e) {
			logger.error("modifyConfig", e);
		}
	}

	/**
	 * 返回 Properties
	 * 
	 * @param fileName
	 *            文件名 (注意：加载的是src下的文件,如果在某个包下．请把包名加上)
	 * @return
	 */
	public static Properties getProperties(String filepath) {
		Properties prop = new Properties();
		try {
			Reader reader = Resources.getResourceAsReader(filepath);
			prop.load(reader);
		} catch (Exception e) {
			logger.error("getProperties", e);
		}
		return prop;
	}

	/**
	 * 获取默认的配置文件
	 * 
	 * @return Properties
	 */
	public static Properties getProperties() {
		return getProperties("/jeesite.properties");
	}

}
