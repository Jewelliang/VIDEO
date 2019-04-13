package com.zhiyou.video.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou.video.model.Admin;
import com.zhiyou.video.security.UsernamePasswordToken;
import com.zhiyou.video.service.AdminService;
import com.zhiyou.video.util.MD5Util;
import com.zhiyou.video.util.StringUtils;
import com.zhiyou.video.util.UserUtils;



/** 
* @author JWL
* @Time 2017年8月16日 上午11:11:16  
*
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminservice;

	@RequestMapping("/login.action")
	public String loginIndex(){
		return "/admin/login";
	}
	
	@RequestMapping(value="/login.action",method=RequestMethod.POST)           
	public String longin(String loginName, String loginPwd,Boolean rememberMe,Model model,HttpServletRequest request,HttpSession session){
		
		if(rememberMe == null){
			rememberMe = false;
		}
		String error = null;
		
		Subject subject = SecurityUtils.getSubject();
		String host =StringUtils.getRemoteAddr((HttpServletRequest)request);
		UsernamePasswordToken token = new UsernamePasswordToken(loginName,loginPwd.toCharArray(),rememberMe,host,true);
		
		try{
			subject.login(token);
		}catch(UnknownAccountException | IncorrectCredentialsException e1){
			error = "用户名或密码错误";
		}catch(ExcessiveAttemptsException e){
			error="超过了尝试的次数，您的账户已经被锁定。";
		}catch(AuthenticationException e){
			
			if(e.getMessage()!=null)
				error = e.getMessage();
			else
				error = "发生错误，无法登录。";		
		}
		
		if (error == null) {
        	
        	session.setAttribute("sysUser", UserUtils.getSubject().getPrincipal());
        	session.setAttribute("loginName", loginName);
        	session.setMaxInactiveInterval(1000);
        	System.out.println("session的类型:"+session.getClass().getName());
        	return "redirect:/admin/video/index.action"; 
        	
		}
        model.addAttribute("message", error);
        return "/admin/login";
		
	}
		
		
		
//		Admin admin = adminservice.getAdminByLoginName(loginName);
//		
//		if(admin == null){
//			
//			model.addAttribute("message", "用户名不存在");
//			
//		}else {
//			if(MD5Util.getMd5Simple(loginPwd).equals(admin.getPassword())){
//				
//				session.setAttribute("admin", admin);
//				
//				return "redirect:/admin/speaker/index.action";
//			
//			}else{
//				model.addAttribute("message", "密码错误");
//			}
//		}	                         
//		return "/admin/login";
//	}
	
	@RequestMapping("/logout.action")
	public String logout(HttpSession session){
		session.setAttribute("sysUser", null);
		UserUtils.getSubject().logout();
		
		return "/admin/login";
	}
	
	/*@RequestMapping("/logout.action")
	public String aaa(HttpSession session){
		session.invalidate();
		return "/admin/login";
		
	
	}*/
	
	
	
	
}
