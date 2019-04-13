package com.zhiyou.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.mapper.SysUserMapper;
import com.zhiyou.video.model.SysUser;
import com.zhiyou.video.service.SysUserService;

/** 
* @author JWL
* @Time 2017年8月25日 下午2:44:23  
*
*/
@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper sysusermapper;
	
	@Override
	public List<SysUser> getAllSysUser() {
		// TODO Auto-generated method stub
		return sysusermapper.getAllSysUser();
	}

	@Override
	public void addSysUser(SysUser sysuser) {
		// TODO Auto-generated method stub
		sysusermapper.addSysUser(sysuser);
	}
	
	public void deleteSysUser(Integer id){
		sysusermapper.deleteSysUser(id);
	}
}
