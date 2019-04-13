package com.zhiyou.video.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.SysUser;

/** 
* @author JWL
* @Time 2017年8月25日 下午2:44:10  
*
*/
public interface SysUserService {

	public List<SysUser> getAllSysUser();
	
	public void addSysUser(SysUser sysuser);
	
	public void deleteSysUser(Integer id);
	
}
