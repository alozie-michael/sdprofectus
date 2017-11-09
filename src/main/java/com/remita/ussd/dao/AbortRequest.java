package com.remita.ussd.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbortRequest {

    private String timeStamp;
    private String sessionID;
    private String cpId;
    private String cpPassword;
    @JsonProperty("MSISDN")
    private String msisdn;
    private String abortReason;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
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

    public String getAbortReason() {
        return abortReason;
    }

    public void setAbortReason(String abortReason) {
        this.abortReason = abortReason;
    }
}
