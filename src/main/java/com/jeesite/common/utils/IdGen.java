package com.jeesite.common.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @since JDK 1.7.0+
 * @version V1.2.1 2017年3月18日
 * @author www.jeegit.org
 */
@Service
@Lazy(false)
public class IdGen implements IdGenerator, SessionIdGenerator {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(randomBase62(64));
		System.out.println(randomBase62(108));
		System.out.println(randomBase62(4));
		System.out.println(randomBase62(4));
		System.out.println(randomBase62(4));
		System.out.println(randomBase62(4));
		System.out.println(randomBase62(4));
		// System.out.println(IdGen.uuid());
		// System.out.println(IdGen.uuid().length());
		// System.out.println(new IdGen().getNextId());
		// for (int i = 0; i < 1000; i++) {
		// System.out.println(IdGen.randomLong() + " " + IdGen.randomBase62(5));
		// }
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 * 
	 * @return String
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 * 
	 * @param length
	 * @return
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	/**
	 * Activiti ID 生成
	 */
	@Override
	public String getNextId() {
		return IdGen.uuid();
	}

	@Override
	public Serializable generateId(Session session) {

		return IdGen.uuid();
	}

}
