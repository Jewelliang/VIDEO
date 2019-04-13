package com.zhiyou.video.util;

import java.util.UUID;

public class RedisTokenManager {

	public static String createToken(Long userId) {
		String token = UUID.randomUUID().toString().replace("-", "");
		JedisUtils.setObject(token, userId, 3600 * 2);
		return token;
	}

	public static Long getUserId(String token) {

		Long userId = (Long) JedisUtils.getObject(token);
		if(userId == null)
		{
			return null;
		}
		// 访问一次，更新一次过期时间
		JedisUtils.setObject(token, userId, 3600 * 2);
		return userId;
	}

	public static void deleteToken(String token) {
		JedisUtils.del(token);
	}
}
