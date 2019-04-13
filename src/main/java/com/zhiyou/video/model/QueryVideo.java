package com.zhiyou.video.model;
/** 
* @author JWL
* @Time 2017年8月18日 下午7:17:43  
*
*/
public class QueryVideo {
	
	private String queryName;
	private int speakerId;
	private int courseId;
	private int subjectId;
	
	
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public int getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(int speakerId) {
		this.speakerId = speakerId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	
	
}
