package com.remita.ussd.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushSMSRequest {

    private String TimeStamp;
    private String SessionId;
    private String CpId;
    private String CpPassword;
    @JsonProperty("MSISDN")
    private String MSISDN;
    private String ServiceCode;
    private Integer MsgCoding;
    private String SMSContent;

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

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getServiceCode() {
        return ServiceCode;
    }

    public void setServiceCode(String serviceCode) {
        ServiceCode = serviceCode;
    }

    public Integer getMsgCoding() {
        return MsgCoding;
    }

    public void setMsgCoding(Integer msgCoding) {
        MsgCoding = msgCoding;
    }

    public String getSMSContent() {
        return SMSContent;
    }

    public void setSMSContent(String SMSContent) {
        this.SMSContent = SMSContent;
    }
}
