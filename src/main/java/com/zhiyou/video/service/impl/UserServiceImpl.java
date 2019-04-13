package com.zhiyou.video.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.mapper.UserMapper;
import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.model.User;
import com.zhiyou.video.service.UserService;

/** 
* @author JWL
* @Time 2017骞�8鏈�21鏃� 涓嬪崍9:50:15  
*
*/
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper usermapper;
	
	
	
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return usermapper.getUserByEmail(email);
	}

	@Override
	public boolean registUser(User user) {
		// TODO Auto-generated method stub
		return usermapper.registUser(user);
	}
	
	public boolean updateUser(Map<String, Object> user){
		return usermapper.updateUser(user);
	}

	@Override
	public List<Course> getCourseById(Integer id) {
		// TODO Auto-generated method stub
		return usermapper.getCourseById(id);
	}

	@Override
	public Subject getSubjectById(Integer id) {
		// TODO Auto-generated method stub
		return usermapper.getSubjectById(id);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return usermapper.updateUser(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return usermapper.getAllUser();
	}

	@Override
	public boolean deleteUser(Integer id) {
		// TODO Auto-generated method stub
		return usermapper.deleteUser(id);
	}

	
		
}
