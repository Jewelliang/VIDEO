package com.zhiyou.video.model;
/** 
* @author JWL
* @Time 2017年8月15日 下午5:25:10  
*
*/
public class Subject {
	private int id;
	private String subjectName;
	private int status;
	
	
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + id + ", subjectName=" + subjectName + ", status=" + status + "]";
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
