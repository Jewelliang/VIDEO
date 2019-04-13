package com.zhiyou.video.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.mapper.CourseMapper;
import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.service.CourseService;

/** 
* @author JWL
* @Time 2017年8月17日 下午2:41:53  
*
*/

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper coursemapper;
	
	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return coursemapper.getAllCourses();
	}
	
	@Override
	public List<Course> getCourseBySubjectId(Integer id) {
		// TODO Auto-generated method stub
		return coursemapper.getCourseBySubjectId(id);
	}
	
	@Override
	public Course getCourseById(Integer id) {
		// TODO Auto-generated method stub
		return coursemapper.getCourseById(id);
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		coursemapper.addCourse(course);
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		coursemapper.updateCourse(course);
	}

	@Override
	public void deleteCourse(Integer id) {
		// TODO Auto-generated method stub
		coursemapper.deleteCourse(id);
	}



	
	
	
}
