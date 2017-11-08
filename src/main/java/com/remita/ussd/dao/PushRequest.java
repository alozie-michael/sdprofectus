package com.remita.ussd.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PushRequest {
	
	private String TimeStamp;
	private String SessionId;
	private String CpId;
	private String CpPassword;
	@JsonProperty("MSISDN")
	private String MSISDN;
	private String ServiceCode;
	private Integer MsgType;
	private Integer OpType;
	private Integer MsgCoding;
	private String UssdContent;
	
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public String getCpId() {
		return CpId;
	}
	public void setCpId(String cpId) {
		CpId = cpId;
	}
	public String getCpPassword() {
		return CpPassword;
	}
	public void setCpPassword(String cpPassword) {
		CpPassword = cpPassword;
	}
	public String getMSISDN() {
		return MSISDN;
	}
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}
	public String getServiceCode() {
		return ServiceCode;
	}
	public void setServiceCode(String serviceCode) {
		ServiceCode = serviceCode;
	}
	public Integer getMsgType() {
		return MsgType;
	}
	public void setMsgType(Integer msgType) {
		MsgType = msgType;
	}
	public Integer getOpType() {
		return OpType;
	}
	public void setOpType(Integer opType) {
		OpType = opType;
	}
	public Integer getMsgCoding() {
		return MsgCoding;
	}
	public void setMsgCoding(Integer msgCoding) {
		MsgCoding = msgCoding;
	}
	public String getUssdContent() {
		return UssdContent;
	}
	public void setUssdContent(String ussdContent) {
		UssdContent = ussdContent;
	}

	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}

}
