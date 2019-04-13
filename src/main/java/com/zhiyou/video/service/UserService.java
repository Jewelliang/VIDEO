package com.zhiyou.video.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.model.User;

/** 
* @author JWL
* @Time 2017年8月21日 下午9:50:00  
*
*/
public interface UserService {
	
	public List<User> getAllUser();
	
	public User getUserByEmail(@Param("email")String email);
	
	public boolean registUser(User user);
	
	public boolean updateUser(User user);
	

	public List<Course> getCourseById(Integer id);
	
	public Subject getSubjectById(Integer id);

	public boolean deleteUser(Integer id);
}
