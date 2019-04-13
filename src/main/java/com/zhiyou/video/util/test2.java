package com.zhiyou.video.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class test2 {

	public static void main(String[] args) {
		
		String algorithmName = "md5";  
		String username = "admin";  
		String password = "123456";  
		String salt1 = username;  
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex(); 
		System.out.println(salt2);
		int hashIterations = Global.hashIterations;  
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);  
		String encodedPassword = hash.toHex(); 
		System.out.println(encodedPassword);
		
	}
}
