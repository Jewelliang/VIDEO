package com.zhiyou.video.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.mapper.AdminMapper;
import com.zhiyou.video.model.Admin;
import com.zhiyou.video.service.AdminService;

/** 
* @author JWL
* @Time 2017年8月16日 上午11:56:55  
*
*/
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminmapper;
	
	@Override
	public Admin getAdminByLoginName(String loginName) {
		
		Admin admin = adminmapper.getAdminByLoginName(loginName);
		return admin;
	}
	
}
