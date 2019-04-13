package com.zhiyou.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @author JWL
* @Time 2017年8月16日 上午11:11:26  
*
*/
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
//		return "redirect:/admin/login.action";
		return "redirect:/front/index.action";
//		return "/admin/sysuser/add";
	}
	
}
