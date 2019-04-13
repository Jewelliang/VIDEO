package com.zhiyou.video.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Admin;

/** 
* @author JWL
* @Time 2017年8月16日 上午11:49:44  
*
*/
public interface AdminMapper {
	
	public Admin getAdminByLoginName(@Param("adminName")String loginName);
	
}
