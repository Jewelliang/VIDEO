package com.zhiyou.video.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou.video.model.Course;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.service.CourseService;
import com.zhiyou.video.service.SubjectService;

/** 
* @author JWL
* @Time 2017年8月17日 下午2:40:56  
*
*/

@Controller
@RequestMapping("/admin/course")
public class CourseController extends BaseController{

	@Autowired
	private CourseService courseservice;
	
	@Autowired
	private SubjectService subjectservice;
	
	@RequestMapping("/index.action")
	public String CourseList(Model model){
		
		List<Course> course = courseservice.getAllCourses();
		model.addAttribute("course", course);
		return "/admin/course/index";
	}
	

	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String CourseAddIndex(Model model){
		
		List<Subject> subjects = subjectservice.getAllSubjects();
		model.addAttribute("subjects", subjects);
		
		return "/admin/course/add";
	}
	

	@RequestMapping(value="/add.action",method=RequestMethod.POST)
	public String CourseAdd(Course course){
		
		//course.setInsertTime(new Date(System.currentTimeMillis()));
		courseservice.addCourse(course);
		
		return "redirect:/admin/course/index.action";
		
	}
	

	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String CourseUpdateIndex(Model model,Integer id){
		
		List<Subject> subject = subjectservice.getAllSubjects();
		Course course = courseservice.getCourseById(id);
		
		model.addAttribute("subjects", subject);
		model.addAttribute("course", course);
		
		return "/admin/course/edit";
		
	}
	
	@RequestMapping(value="/edit.action",method=RequestMethod.POST)
	public String CourseUpdate(Course course){
		
		//course.setUpdateTime(new Date(System.currentTimeMillis()));
		
		courseservice.updateCourse(course);
		return "redirect:/admin/course/index.action";
	}
	

	@RequestMapping("/delete.action")
	public String CourseDelete(Integer id){
		
		courseservice.deleteCourse(id);
		return "redirect:/admin/course/index.action";
			
	}
	
	
	
	
}
