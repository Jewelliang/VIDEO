package com.zhiyou.video.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou.video.common.OperationResult;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.model.User;
import com.zhiyou.video.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/index.action")
	public String VideoList(Model model){
		List<User> user = userService.getAllUser();
		model.addAttribute("user", user);
		return "/admin/frontuser/index";
	
	}
	
	@RequestMapping("/delete.action")
	@ResponseBody
	public  OperationResult videoDelete(Integer id){
		OperationResult result=new OperationResult();
		boolean b = userService.deleteUser(id);	
        result.setSuccess(b);
		
		return result;
		
	}
}
