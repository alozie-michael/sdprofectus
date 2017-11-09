package com.remita.ussd.dao;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MtbReportRequest {

    private String timeStamp;
	private String sessionId;
	private String cpId;
	private String cpPassword;
	@JsonProperty("MSISDN")
	private String msisdn;
	@JsonProperty("MTB_Report")
	private String mtbReport;

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

	public String getMtbReport() {
		return mtbReport;
	}

	public void setMtbReport(String mtbReport) {
		this.mtbReport = mtbReport;
	}
}
