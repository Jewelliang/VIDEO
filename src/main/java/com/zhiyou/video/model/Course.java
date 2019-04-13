package com.zhiyou.video.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/** 
* @author JWL
* @Time 2017年8月15日 下午5:20:32  
*
*/
public class Course {
	private int id;
	private String courseName;
	private String courseDescr;
	private int subjectId;
	private String subjectName;
	private int status;
	private Timestamp insertTime;
	private Timestamp updateTime;
	
	private List<Video> videoList;
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", courseDescr=" + courseDescr
				+ ", subjectId=" + subjectId + ", subjectName=" + subjectName + ", status=" + status + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", videoList=" + videoList + "]";
	}
	
	
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getCourseDescr() {
		return courseDescr;
	}




	public void setCourseDescr(String courseDescr) {
		this.courseDescr = courseDescr;
	}




	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public List<Video> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}
	
	
	
	public Timestamp getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
