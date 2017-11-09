package com.remita.ussd.dao;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PullResponse {
	
	private String sessionId;
	private String msisdn;
	private String errorCode;
	private String errorMsg;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}

}
