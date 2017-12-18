package com.lms.utils.redis;

import java.util.Set;

import com.lms.utils.common.ConstantsUtil;
import com.lms.utils.common.StringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private static JedisPool pool = null;
	private static StringUtil stringUtil = new StringUtil();
	static {
		JedisPoolConfig poolConfig = new JedisPoolConfig();

		poolConfig.setMaxIdle(300);
		poolConfig.setMaxTotal(500);
		poolConfig.setMinIdle(8);
		poolConfig.setTestOnBorrow(true);
		pool = new JedisPool(poolConfig, ConstantsUtil.redisUrl, ConstantsUtil.redisPort, 10000, ConstantsUtil.redisPassword, 2);
	}

	/**
	 * 判断是否添加验证码信息成功
	 * 
	 * @param email
	 * @param code
	 * @param type
	 * @return 添加成功返回true，添加失败返回false
	 */
	public static boolean insertEmailCaptcha(String email, String code, String type) {
		Jedis jedis = pool.getResource();
		boolean flag = false;
		String key = email + "_" + type;
		String result = jedis.set(key, code);
		System.out.println(result);
		if (result.equals("OK")) {
			flag = true;
			jedis.expire(key, ConstantsUtil.emailCaptchaTime / 1000);
		}
		jedis.close();
		// pool.close();
		return flag;
	}

	/**
	 * 检测邮箱验证码是否正确
	 * 
	 * @param email
	 * @param code
	 * @param type
	 * @return 验证通过返回1，验证码不正确返回0，验证码失效返回2
	 */
	public static int checkEmailCaptcha(String email, String code, String type) {
		int rs = 0;
		Jedis jedis = pool.getResource();
		String key = email + "_" + type;
		String lowerCode = code.toLowerCase();
		String redisCode = jedis.get(key);
		if (stringUtil.isNullString(redisCode)) {
			rs = 0;
			return rs;
		} else {
			long leftTime = jedis.pttl(key);
			redisCode = redisCode.toLowerCase();

			if (lowerCode.equals(redisCode) && leftTime >= 0) {
				rs = 1;
			} else if (lowerCode.equals(redisCode) && leftTime < 0) {
				rs = 2;
			}
			jedis.close();
			// pool.close();
			return rs;
		}

	}

	/**
	 * 删除使用过的邮箱验证码
	 * 
	 * @param email
	 * @param type
	 * @return 删除成功返回true,删除失败返回false
	 */
	public static boolean deleteEmailCaptcha(String email, String type) {
		boolean flag = false;
		long rs = 0l;
		Jedis jedis = pool.getResource();
		String key = email + "_" + type;
		rs = jedis.del(key);
		if (rs > 0) {
			flag = true;
		}
		jedis.close();
		return flag;
	}

	/**
	 * 判断是否添加token白名单成功
	 * 
	 * @param uri
	 * @return 添加成功返回true，添加失败返回false
	 */
	public static boolean addApiWhiteList(String uri) {
		boolean flag = false;
		Jedis jedis = pool.getResource();
		Long status = jedis.sadd("api_white_list", uri);
		if (status > 0) {
			flag = true;
		}
		jedis.close();
		// pool.close();
		return flag;
	}

	/**
	 * 判断是否删除接口白名单成功
	 * 
	 * @param uri
	 * @return 删除成功返回true，删除失败返回false
	 */
	public static boolean deleteApiWhiteList(String uri) {
		Jedis jedis = pool.getResource();
		boolean flag = false;
		Long status = jedis.srem("api_white_list", uri);
		if (status > 0) {
			flag = true;
		}
		jedis.close();
		// pool.close();
		return flag;
	}

	/**
	 * 判断api是否在token验证白名单中
	 * 
	 * @param uri
	 * @return 在白名单返回true，不在返回false
	 */
	public static boolean checkApiWhiteList(String uri) {
		Jedis jedis = pool.getResource();
		boolean flag = false;
		Set<String> resultData = jedis.smembers("api_white_list");
		//System.out.println("redisData:"+resultData);
		if (resultData.contains(uri)) {
			flag = true;
		}
		jedis.close();
		// pool.close();
		return flag;

	}

	/**
	 * 判断是否能够再次发送邮箱验证码
	 * 
	 * @param email
	 * @param type
	 * @return 可以发送返回true，不能发送返回false
	 */
	public static boolean sendCaptchaAgain(String email, String type) {
		Jedis jedis = pool.getResource();
		boolean flag = false;
		long remainTime = jedis.pttl(email + "_" + type);
		// System.out.println(remainTime +
		// ConstantsUtil.sendCaptchaAgainTime+"\n"+ConstantsUtil.emailCaptchaTime);
		if ((remainTime + ConstantsUtil.sendCaptchaAgainTime) < ConstantsUtil.emailCaptchaTime) {
			flag = true;
		}
		jedis.close();
		// pool.close();
		return flag;

	}

	public static void main(String[] args) {
		// Jedis jedis = pool.getResource();
		// Set<String> teString = jedis.smembers("frejg");
		// boolean flag = teString.contains("abc");
		// System.out.println(flag);
		// System.out.println(insertEmailCaptcha("test8", "1234", "reset"));
		// System.out.println(addApiWhiteList("test2"));
		// System.out.println(checkApiWhiteList("test2"));
		// System.out.println(deleteApiWhiteList("test1"));
		// System.out.println(checkEmailCaptcha("test8", "1234", "reset"));
		// String email = "test@qq.com";
		// System.out.println(sendCaptchaAgain("test8", "reset"));
		// String code = "1234";
		// insertEmailCaptcha(email, code ,"reset");
		// checkEmailCaptcha(email, code, "reset");
		// String uri = "cerg";
//		 String[] uri = new String[]{"data1","data2","data3"};
//		 addApiWhiteList(uri);
		// boolean flag = checkApiWhiteList(uri, method);
		// System.out.println(flag);
	}

	// public long findTimeLeft(String email){}
}
