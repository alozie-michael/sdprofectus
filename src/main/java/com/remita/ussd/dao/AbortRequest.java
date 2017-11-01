package com.remita.ussd.dao;

public class AbortRequest {

    private String TimeStamp;
    private String SessionID;
    private String CpId;
    private String CpPassword;
    private String MSISDN;
    private String AbortReason;

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        SessionID = sessionID;
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

    public String getAbortReason() {
        return AbortReason;
    }

    public void setAbortReason(String abortReason) {
        AbortReason = abortReason;
    }
}
