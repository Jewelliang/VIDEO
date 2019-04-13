package com.zhiyou.video.model;
/** 
* @author JWL
* @Time 2017年8月15日 下午5:17:22  
*
*/
public class Admin {
	private int id;
	private String loginName;
	private String loginPwd;
	private String role;
	private int stauts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getStauts() {
		return stauts;
	}
	public void setStauts(int stauts) {
		this.stauts = stauts;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", loginName=" + loginName + ", loginPwd=" + loginPwd + ", role=" + role
				+ ", stauts=" + stauts + "]";
	}
	

	
	
	
}
