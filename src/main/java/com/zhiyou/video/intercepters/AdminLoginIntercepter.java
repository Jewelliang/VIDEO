package com.zhiyou.video.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou.video.model.Admin;
import com.zhiyou.video.security.SystemAuthorizingRealm.Principal;

/** 
* @author JWL
* @Time 2017年8月16日 下午2:04:38  
*
*/
public class AdminLoginIntercepter implements HandlerInterceptor{

	
	//到达controller方法之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		
		
		HttpSession session =  request.getSession();
		//Admin admin = (Admin) session.getAttribute("admin");
		Principal admin = (Principal) session.getAttribute("sysUser");
		
		if(admin != null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/login.action");
		}
		return false;
	}
	
	//到达controller 方法之后，渲染视图之前
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
			
	}
		
	//渲染视图之后调用
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	

	
	

}
