package com.zhiyou.video.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.model.User;
import com.zhiyou.video.model.Video;

/** 
* @author JWL
* @Time 2017年8月21日 下午5:41:58  
*
*/

public interface UserMapper {
	
	public User getUserByEmail(@Param("email")String email);
	
	public boolean registUser(User user);
	
//	public boolean updateUser(User user);
	public boolean updateUser(Map<String,Object> map);
	
	public List<Course> getCourseById(@Param("subjectId")Integer id);
	
	public Subject getSubjectById(@Param("subjectId")Integer id);
	
	public Video getVideoById(Integer videoId);
	
	public List<Video> getAllVideo(Integer courseId);
	
	public boolean updateUser(User user);
	
	public List<User> getAllUser() ;
	
	public boolean deleteUser(Integer id) ;

}
