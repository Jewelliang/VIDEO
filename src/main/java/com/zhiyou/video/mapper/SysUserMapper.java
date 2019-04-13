package com.zhiyou.video.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.SysUser;


/** 
* @author JWL
* @Time 2017年8月23日 下午2:53:39  
*
*/

public interface SysUserMapper {
	public SysUser getUserByName(@Param("username")String userName);
	
	public List<SysUser> getAllSysUser();
	
	public void addSysUser(SysUser sysuser);
	
	public void deleteSysUser(@Param("sysUserId")Integer id);
	
}
