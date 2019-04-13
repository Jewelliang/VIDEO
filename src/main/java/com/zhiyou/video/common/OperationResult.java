package com.zhiyou.video.common;
/** 
* @author JWL
* @Time 2017年8月19日 上午10:04:52  
*
*/
public class OperationResult {
	
	private String errorCode;
	
	private String errorMessage;
	
	private Object content;
	
	private Boolean success;
	
	

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "OperationResult [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", content=" + content
				+ ", success=" + success + "]";
	}
	
	
	
	
	
}
