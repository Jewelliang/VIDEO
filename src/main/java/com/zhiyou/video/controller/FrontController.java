package com.zhiyou.video.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;
import com.mysql.fabric.xmlrpc.base.Data;
import com.zhiyou.video.common.OperationResult;
import com.zhiyou.video.model.QueryVideo;
import com.zhiyou.video.model.User;
import com.zhiyou.video.service.UserService;
import com.zhiyou.video.util.FileUtil;
import com.zhiyou.video.util.MD5Util;

/** 
* @author JWL
* @Time 2017年8月21日 下午5:04:14  
*
*/

@Controller
@RequestMapping("/front")
public class FrontController extends BaseController{
	
	@Autowired
	private UserService userservice;
	
	
	@RequestMapping("/index.action")
	public String frontIndex(){
		return "/front/index";
	}
	

	@RequestMapping(value="/user/login.action",method=RequestMethod.POST)
	@ResponseBody
	public OperationResult loginIndex(String email,String password,HttpSession session,ServletRequest request, ServletResponse response){
		
		User user  = userservice.getUserByEmail(email);
	
        System.out.println("/user/login.action  "+user.getEmail());
         
		OperationResult result = new OperationResult();
		
		if(email==null || email.equals("")){
			result.setErrorMessage("账号不能为空！");
		}else {
			if(MD5Util.getMd5Simple(password).equals(user.getPassWord())){
				
				session.setAttribute("user", user);
				session.setAttribute("email", email);
				session.setMaxInactiveInterval(2000);
				result.setContent(user);
				result.setSuccess(true);
			}else{
				result.setErrorMessage("密码错误！");
			}
		}
		
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
//		
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		httpServletRequest.setAttribute("videoUser", user.getEmail());
		return result;			
	}
	
	
	@RequestMapping("/user/logout.action")
	public String logout(HttpSession session){
		
		session.invalidate();
		
		return "redirect:/front/index.action";
		
	}
}











