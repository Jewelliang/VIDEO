package com.zhiyou.video.model;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou.video.common.ValidGroup1;


/** 
* @author JWL
* @Time 2017年8月15日 下午5:26:47  
*
*/
public class Video {
	private int id;
	private String videoTitle;
	private String videoUrl;
	private int speakerId;
	private int courseId;
	private int subjectId;
	private String videoDescr;
	@Range(min=150,message="{videoLength.error}", groups={ValidGroup1.class})
	private int videoLength;
	private String videoPlayTimes;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private String videoImageUrl;
	private String speakerName;
	private String courseName;
	private String subjectName;
	private String videoLengthStr; //添加视频时长格式化字符串
	
	private MultipartFile icon; 
	private MultipartFile video1;
	
	@Override
	public String toString() {
		return "Video [id=" + id + ", videoTitle=" + videoTitle + ", videoUrl=" + videoUrl + ", speakerId="
				+ speakerId + ", courseId=" + courseId + ", subjectId=" + subjectId + ", videoDescr=" + videoDescr
				+ ", videoLength=" + videoLength + ", videoPlayTimes=" + videoPlayTimes + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", videoImageUrl=" + videoImageUrl + ", speakerName=" + speakerName
				+ ", courseName=" + courseName + ", subjectName=" + subjectName + ", videoLengthStr=" + videoLengthStr
				+ ", icon=" + icon + ", video1=" + video1 + "]";
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getVideoDescr() {
		return videoDescr;
	}



	public void setVideoDescr(String videoDescr) {
		this.videoDescr = videoDescr;
	}



	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
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
	
	public int getVideoLength() {
		return videoLength;
	}
	public void setVideoLength(int videoLength) {
		this.videoLength = videoLength;
	}
	public String getVideoPlayTimes() {
		return videoPlayTimes;
	}
	public void setVideoPlayTimes(String videoPlayTimes) {
		this.videoPlayTimes = videoPlayTimes;
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
	public String getVideoImageUrl() {
		return videoImageUrl;
	}
	public void setVideoImageUrl(String videoImageUrl) {
		this.videoImageUrl = videoImageUrl;
	}
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getVideoLengthStr() {
		return videoLengthStr;
	}
	public void setVideoLengthStr(String videoLengthStr) {
		this.videoLengthStr = videoLengthStr;
	}
	public MultipartFile getIcon() {
		return icon;
	}
	public void setIcon(MultipartFile icon) {
		this.icon = icon;
	}
	public MultipartFile getVideo1() {
		return video1;
	}
	public void setVideo1(MultipartFile video1) {
		this.video1 = video1;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	} 
	
	

	
}
