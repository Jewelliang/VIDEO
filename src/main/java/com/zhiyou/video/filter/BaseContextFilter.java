package com.zhiyou.video.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("*.action") 
public class BaseContextFilter implements Filter {

    

	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
		String path = req.getContextPath();
		req.setAttribute("BaseContext", basePath+path+"/");
		
		System.out.println("getScheme========"+req.getScheme());
		System.out.println("getServerName========"+req.getServerName());
		System.out.println("getServerPort========"+req.getServerPort());
		System.out.println("========"+req.getContextPath());
		chain.doFilter(req, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	
	}

}
