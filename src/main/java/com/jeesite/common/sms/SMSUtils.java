package com.jeesite.common.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.jeesite.common.security.Digests;

/**
 * 功能: 企信通PHP HTTP接口 发送短信</br>
 * 修改日期: 2014-03-19</br>
 * 说明:</br>
 * http://api.cnsms.cn/?ac=send&uid=用户账号&pwd=MD5位32密码&mobile=号码&content=内容</br>
 * 状态:</br>
 * 100 发送成功</br>
 * 101 验证失败</br>
 * 102 短信不足</br>
 * 103 操作失败</br>
 * 104 非法字符</br>
 * 105 内容过多</br>
 * 106 号码过多</br>
 * 107 频率过快</br>
 * 108 号码内容空</br>
 * 109 账号冻结</br>
 * 110 禁止频繁单条发送</br>
 * 111 系统暂定发送</br>
 * 112 号码不正确</br>
 * 120 系统升级</br>
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月17日
 * @author www.jeegit.org
 */
public class SMSUtils {

	/**
	 * 发送短信，uid，pwd，参数值请向企信通申请， tel：发送的手机号， content:发送的内容
	 * 
	 * @param uid
	 * @param pwd
	 * @param tel
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static String send(String uid, String pwd, String tel, String content) throws IOException {

		/**
		 * 创建StringBuffer对象用来操作字符串
		 */
		StringBuffer sb = new StringBuffer("http://api.cnsms.cn/?");

		/**
		 * 向StringBuffer追加用户名
		 */
		sb.append("ac=send&uid=" + uid);// 在此申请企信通uid，并进行配置用户名

		/**
		 * 向StringBuffer追加密码（密码采用MD5 32位 小写）
		 */
		sb.append("&encode=utf8");

		/**
		 * 向StringBuffer追加密码（密码采用MD5 32位 小写）
		 */
		sb.append("&pwd=" + Digests.string2MD5(pwd));// 在此申请企信通uid，并进行配置密码

		/**
		 * 向StringBuffer追加手机号码
		 */
		sb.append("&mobile=" + tel);

		/**
		 * 向StringBuffer追加消息内容转URL标准码
		 */
		sb.append("&content=" + URLEncoder.encode(content, "utf8"));

		/**
		 * 创建url对象
		 */
		URL url = new URL(sb.toString());

		/**
		 * 打开url连接
		 */
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		/**
		 * 设置url请求方式 ‘get’ 或者 ‘post’
		 */
		connection.setRequestMethod("POST");

		/**
		 * 发送
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		/**
		 * 返回发送结果
		 */
		String inputline = in.readLine();
		return inputline;

	}

}