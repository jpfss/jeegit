package com.jeesite.common.json;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeesite.common.mapper.JsonMapper;

/**
 * $.ajax后需要接受的JSON
 *
 * @since JDK 1.7.0+
 * @version V1.2.1
 * @author www.jeegit.org
 */
public class AjaxJson {

	/**
	 * 是否成功
	 */
	private boolean success = true;

	/**
	 * 错误代码 默认值 -1
	 */
	private String errorCode = "-1";

	/**
	 * 提示信息
	 */
	private String msg = "操作成功";

	/**
	 * 通过map封装 json
	 */
	private LinkedHashMap<String, Object> body = new LinkedHashMap();

	public LinkedHashMap<String, Object> getBody() {
		return body;
	}

	public void setBody(LinkedHashMap<String, Object> body) {
		this.body = body;
	}

	/**
	 * 向json中添加属性，在js中访问，请调用data.map.key
	 * 
	 * @param key
	 *            键值名
	 * @param value
	 *            键值
	 */
	public void put(String key, Object value) {
		body.put(key, value);
	}

	public void remove(String key) {
		body.remove(key);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 返回对象时忽略此属性
	 * 
	 * @return 返回json字符串数组，将访问msg和key的方式统一化，都使用data.key的方式直接访问。
	 */
	@JsonIgnore
	public String getJsonStr() {

		String json = JsonMapper.getInstance().toJson(this);
		return json;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
