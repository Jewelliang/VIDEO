package com.zhiyou.video.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Speaker;

/** 
* @author JWL
* @Time 2017年8月17日 下午2:41:34  
*
*/
public interface CourseService {
	
	public List<Course> getAllCourses();
	
	public List<Course> getCourseBySubjectId(@Param("subjectId")Integer id);
	
	public Course getCourseById(Integer id);
	
	public void addCourse(Course course);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(Integer id);
}
