package com.zhiyou.video.security;

/**
 * 用户和密码（包含验证码）令牌类
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private boolean mobileLogin;
	
	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password, 
			boolean rememberMe, String host, boolean mobileLogin) {
		super(username, password, rememberMe, host);
		this.mobileLogin = mobileLogin;
	}

	

	public boolean isMobileLogin() {
		return mobileLogin;
	}
	
}