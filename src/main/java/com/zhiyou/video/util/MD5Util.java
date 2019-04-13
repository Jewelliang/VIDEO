package com.zhiyou.video.util;

import org.springframework.util.DigestUtils;

/** 
* @author JWL
* @Time 2017年8月16日 下午8:03:01  
*
*/
public class MD5Util {
	
	
	public static String getMd5Simple(String password){
		String md502 = DigestUtils.md5DigestAsHex(password.getBytes());
		return md502;
	}
	
	public static void main(String[] args){
		System.out.println(getMd5Simple("123456"));
	}
	
}
