package com.remita.ussd.dao;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MtbReportRequest {

    private String TimeStamp;
	private String SessionId;
	private String CpId;
	private String CpPassword;
	@JsonProperty("MSISDN")
	private String MSISDN;
	private String MTB_Report;

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

	public String getMTB_Report() {
		return MTB_Report;
	}

	public void setMTB_Report(String MTB_Report) {
		this.MTB_Report = MTB_Report;
	}
}
