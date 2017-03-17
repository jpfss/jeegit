package com.jeesite.common.mail;

import java.util.Properties;

/**
 * 邮件内容对象
 *
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
public class MailBody {

	/**
	 * 发送邮件服务器的IP或者域名
	 */
	private String mailServerHost;

	/**
	 * 发送邮件服务器的域名
	 */
	private String mailServerPort = "25";

	/**
	 * 邮件发送者的地址
	 */
	private String fromAddress;

	/**
	 * 邮件接收者的地址
	 */
	private String toAddress;

	/**
	 * 登陆邮件发送服务器的用户名
	 */
	private String userName;
	/**
	 * 登陆邮件发送服务器的密码
	 */
	private String password;

	/**
	 * 是否需要身份验证
	 */
	private boolean validate = false;

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件的文本内容
	 */
	private String content;

	/**
	 * 邮件附件的文件名
	 */
	private String[] attachFileNames;

	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	/**
	 * 获取邮件域名
	 * 
	 * @return 服务器域名
	 */
	public String getMailServerHost() {
		return mailServerHost;
	}

	/**
	 * 设置邮件域名
	 * 
	 * @param mailServerHost
	 *            域名或者ip地址
	 */
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames) {
		this.attachFileNames = fileNames;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String textContent) {
		this.content = textContent;
	}
}
