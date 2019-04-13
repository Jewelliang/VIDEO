package com.zhiyou.video.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Role;

/** 
* @author JWL
* @Time 2017年8月23日 下午2:57:00  
*
*/
public interface SysRoleMapper {
	
	List<Role> getRolesByIds(@Param("roleIds") List<Integer> roleIds);
	
	public List<Role> getAllRoles();
}
