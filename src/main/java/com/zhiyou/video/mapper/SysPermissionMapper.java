package com.zhiyou.video.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Resource;

/** 
* @author JWL
* @Time 2017年8月23日 下午2:57:32  
*
*/
public interface SysPermissionMapper {
	List<Resource> getResourcesByIds(@Param("ids") List<Integer> ids); 
}
