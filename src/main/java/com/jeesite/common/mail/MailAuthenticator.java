package com.jeesite.common.mail;

import javax.mail.*;

/**
 * 实现 javax.mail.Authenticator抽象类
 *
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
public class MailAuthenticator extends Authenticator {

	/**
	 * 用户名
	 */
	private String userName = null;

	/**
	 * 密码
	 */
	private String password = null;

	/**
	 * 默认构造函数
	 */
	public MailAuthenticator() {

	}

	/**
	 * 默认构造函数
	 * 
	 * @param username
	 *            登录名
	 * @param password
	 *            登录密码
	 */
	public MailAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	/**
	 * 验证用户密码
	 */
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
