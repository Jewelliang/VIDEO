package com.zhiyou.video.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class User implements Serializable{
	private int userId;
	private String nickName;
	private String email;
	private String passWord;
	private String sex;
	private String province;
	private String city;
	private Date birthday;
	private String headUrl;
	private Timestamp insertTime;
	private Timestamp updateTime;
	private int status;
	private String captcha;
	private String newPassWord;
	private String newPassWordAgain;

	public String getNewPassWord() {
		return newPassWord;
	}
	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}
	public String getNewPassWordAgain() {
		return newPassWordAgain;
	}
	public void setNewPassWordAgain(String newPassWordAgain) {
		this.newPassWordAgain = newPassWordAgain;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
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
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickName=" + nickName + ", email=" + email + ", passWord=" + passWord
				+ ", sex=" + sex + ", province=" + province + ", city=" + city + ", birthday=" + birthday + ", headUrl="
				+ headUrl + ", insertTime=" + insertTime + ", updateTime=" + updateTime + ", status=" + status
				+ ", captcha=" + captcha + "]";
	}
	
	
	
	
	
	
}
