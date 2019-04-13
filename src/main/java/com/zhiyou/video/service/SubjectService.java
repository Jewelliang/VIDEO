package com.zhiyou.video.service;

import java.util.List;
import java.util.Map;

import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Subject;

/** 
* @author JWL
* @Time 2017年8月17日 下午4:33:02  
*
*/
public interface SubjectService {
	
	public List<Subject> getAllSubjects();
	
	public List<Subject> getAllSubjectById(Integer subjectId);

	public void addSubject(Subject subject);
}
