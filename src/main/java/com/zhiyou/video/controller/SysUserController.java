package com.zhiyou.video.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou.video.model.Role;
import com.zhiyou.video.model.SysUser;
import com.zhiyou.video.service.SysRoleService;
import com.zhiyou.video.service.SysUserService;
import com.zhiyou.video.util.Global;

/** 
* @author JWL
* @Time 2017年8月25日 下午2:14:16  
*
*/
@Controller
@RequestMapping("/admin/sysuser")
public class SysUserController {

		@Autowired
		private SysUserService sysuserservice;
	
		@Autowired
		private SysRoleService sysroleservice;
		
		@RequestMapping("/index.action")
		public String sysUserIndex(Model model){
			
			List<SysUser> sysuser =  sysuserservice.getAllSysUser();
			model.addAttribute("sysuser", sysuser);
			return "admin/sysuser/index";
		}
		
	
		@RequestMapping(value="/add.action",method=RequestMethod.GET)
		public String sysUserAddIndex(Model model){
			
			List<Role> role = sysroleservice.getAllRoles();
			model.addAttribute("role", role);
			return "admin/sysuser/add";
		}
		

		@RequestMapping(value="/add.action",method=RequestMethod.POST)
		public String sysUserAdd(SysUser sysuser){
			
			String algorithmName = "md5";  
	 
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); 
			System.out.println(salt);
			sysuser.setSalt(salt);
			int hashIterations = Global.hashIterations;  
			SimpleHash hash = new SimpleHash(algorithmName, sysuser.getPassword() , sysuser.getUsername() + salt, hashIterations);  
			sysuser.setPassword(hash.toHex());
			
			sysuserservice.addSysUser(sysuser);
			
			return "redirect:/admin/login.action";
		}
		
		
		

		@RequestMapping(value="/delete.action",method=RequestMethod.GET)
		public String sysUserAdd(Integer id ){

			sysuserservice.deleteSysUser(id);
			return "redirect:/admin/sysuser/index.action";
		
		}
		
		
		
		
		
		
		
}
