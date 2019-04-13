package com.zhiyou.video.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.util.WebUtils;

import com.zhiyou.video.model.UserNamePassWordCookieToken;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

//@WebFilter("*.action") 
public class FormAuthenticationCookieFilter  extends FormAuthenticationFilter{
	
	 public static final String DEFAULT_CAPTCHA_PARAM = "ck_rmbUser";//自定义的rememberme在form表单中的name  
	    public static final String DEFAULT_LOGINTYPE_PARAM = "loginType";//自定义的登录类型在form表单中的name  
	  
	    private String isRememberParam = DEFAULT_CAPTCHA_PARAM;  
	    private String loginTypeParam = DEFAULT_LOGINTYPE_PARAM;  
	  
	    public String getIsRememberParam() {  
	  
	        return isRememberParam;  
	  
	    }  
	  
	    public void setIsRememberParam(String isRememberParam) {  
	        this.isRememberParam = isRememberParam;  
	  
	    }  
	  
	    public String getLoginTypeParam() {  
	        return loginTypeParam;  
	    }  
	  
	    public void setLoginTypeParam(String loginTypeParam) {  
	        this.loginTypeParam = loginTypeParam;  
	    }  
	  
	    protected boolean getIsRemember(ServletRequest request) {  
	        return WebUtils.isTrue(request, getIsRememberParam());//利用shiro的工具类来把form表单传来的isRemember转换为boolean值  
	  
	    }  
	  
	    protected String getLoginType(ServletRequest request) {  
	        return WebUtils.getCleanParam(request, getLoginTypeParam());//利用shiro的工具类获得登录类型  
	    }  
	  
	    protected AuthenticationToken createToken(  
	  
	    ServletRequest request, ServletResponse response) {  
	  
	        String username = getUsername(request);  
	        String password = getPassword(request);  
	  
	        boolean isRemember = getIsRemember(request);  
	        String loginType = getLoginType(request);  
	        HttpServletRequest httpServletReqrest = (HttpServletRequest) request;  
	        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
	        try {  
	            if (isRemember) {//创建cookie  
	                Cookie user = new Cookie("user", username + "-" + password+ "-" + loginType);  
	                // user.setMaxAge(60);  
	                user.setMaxAge(365*24*60*60);  
	                httpServletResponse.addCookie(user);  
	            } else {//清除cookie  
	  
	                Cookie[] cookies = httpServletReqrest.getCookies();  
	                for (Cookie cookie : cookies) {  
	                    if (cookie.getName().equals("user")) {  
	                        cookie.setValue(null);  
	                        cookie.setMaxAge(0);// 立即销毁cookie  
	                        System.out.println("被删除的cookie名字为:" + cookie.getName());  
	                        httpServletResponse.addCookie(cookie);  
	                        break;  
	                    }  
	                }  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        boolean rememberMe = isRememberMe(request);//shiro自带的remember  
	  
	        String host = getHost(request);  
	  
	        return new UserNamePassWordCookieToken(username,  
	                password.toCharArray(), rememberMe, host, isRemember, loginType);  
	  
	    }  

}
