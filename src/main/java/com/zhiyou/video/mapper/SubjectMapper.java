package com.zhiyou.video.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Subject;

/** 
* @author JWL
* @Time 2017年8月17日 下午4:34:37  
*
*/
public interface SubjectMapper {

	
	public List<Subject> getAllSubjects();
	
	public List<Subject> getAllSubjectById(@Param("subjectId")Integer subjectId);
	
	public void addSubject(Subject subject);
}
