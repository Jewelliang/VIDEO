package com.zhiyou.video.service;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Admin;

/** 
* @author JWL
* @Time 2017年8月16日 上午11:56:39  
*
*/
public interface AdminService {
	public Admin getAdminByLoginName(@Param("loginName")String loginName);
}
