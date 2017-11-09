package com.remita.ussd.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushSMSRequest {

    private String timeStamp;
    private String sessionId;
    private String cpId;
    private String cpPassword;
    @JsonProperty("MSISDN")
    private String msisdn;
    private String serviceCode;
    private Integer msgCoding;
    @JsonProperty("SMSContent")
    private String smsContent;

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

    public Integer getMsgCoding() {
        return msgCoding;
    }

    public void setMsgCoding(Integer msgCoding) {
        this.msgCoding = msgCoding;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
}
