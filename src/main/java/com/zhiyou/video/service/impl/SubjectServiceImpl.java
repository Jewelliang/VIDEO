package com.zhiyou.video.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.video.mapper.SubjectMapper;
import com.zhiyou.video.model.Speaker;
import com.zhiyou.video.model.Subject;
import com.zhiyou.video.service.SubjectService;


@Service
public class SubjectServiceImpl implements SubjectService{

	
	@Autowired
	private SubjectMapper subjectmapper;
	
	@Override
	public List<Subject> getAllSubjects() {
		return subjectmapper.getAllSubjects();
	}

	@Override
	public List<Subject> getAllSubjectById(Integer subjectId) {
		// TODO Auto-generated method stub
		return subjectmapper.getAllSubjectById(subjectId);
	}

	@Override
	public void addSubject(Subject subject) {
		// TODO Auto-generated method stub
		subjectmapper.addSubject(subject);
	}
	


}
