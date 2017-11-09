package com.remita.ussd.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.el.util.ReflectionUtil;

public class PullRequest {

    private String timeStamp;
	private String sessionId;
	private String cpId;
	private String cpPassword;
	@JsonProperty("MSISDN")
	private String msisdn;
	private String serviceCode;
	private Integer msgType;
	private Integer opType;
	private Integer msgCoding;
	private String ussdContent;
	private Integer network;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getCpPassword() {
        return cpPassword;
    }

    public void setCpPassword(String cpPassword) {
        this.cpPassword = cpPassword;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Integer getMsgCoding() {
        return msgCoding;
    }

    public void setMsgCoding(Integer msgCoding) {
        this.msgCoding = msgCoding;
    }

    public String getUssdContent() {
        return ussdContent;
    }

    public void setUssdContent(String ussdContent) {
        this.ussdContent = ussdContent;
    }

    public Integer getNetwork() {
        return network;
    }

    public void setNetwork(Integer network) {
        this.network = network;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
