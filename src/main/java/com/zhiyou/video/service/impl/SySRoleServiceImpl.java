package com.zhiyou.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.mapper.SysRoleMapper;
import com.zhiyou.video.model.Role;
import com.zhiyou.video.service.SysRoleService;

/** 
* @author JWL
* @Time 2017年8月25日 下午2:52:57  
*
*/
@Service
public class SySRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysrolemapper;
	
	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return sysrolemapper.getAllRoles();
	}

}
