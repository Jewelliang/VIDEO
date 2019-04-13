package com.zhiyou.video.model;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/** 
* @author JWL
* @Time 2017年8月15日 下午5:22:37  
*
*/
public class Speaker {
	private int id;
	private String speakerName;
	private String speakerDescr;
	private String speakerJob;
	private String speakerHeadUrl;
	private MultipartFile icon;   //图片
	private Timestamp insertTime;
	private Timestamp updateTime;
	private int status;
	
	
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpeakerDescr() {
		return speakerDescr;
	}
	public void setSpeakerDescr(String speakerDescr) {
		this.speakerDescr = speakerDescr;
	}
	public String getSpeakerJob() {
		return speakerJob;
	}
	public void setSpeakerJob(String speakerJob) {
		this.speakerJob = speakerJob;
	}
	public String getSpeakerHeadUrl() {
		return speakerHeadUrl;
	}
	public void setSpeakerHeadUrl(String speakerHeadUrl) {
		this.speakerHeadUrl = speakerHeadUrl;
	}
	public MultipartFile getIcon() {
		return icon;
	}
	public void setIcon(MultipartFile icon) {
		this.icon = icon;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	


	
	
}
