package com.remita.ussd.dao;


public class PullRequest {

    private String TimeStamp;
	private String SessionId;
	private String CpId;
	private String CpPassword;
	private String MSISDN;
	private String ServiceCode;
	private Integer MsgType;
	private Integer OpType;
	private Integer MsgCoding;
	private String UssdContent;
	private Integer Network;

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}

	public void setCpId(String cpId) {
		CpId = cpId;
	}

	public void setCpPassword(String cpPassword) {
		CpPassword = cpPassword;
	}

	public void setMSISDN(String MSISDN) {
		this.MSISDN = MSISDN;
	}

	public void setServiceCode(String serviceCode) {
		ServiceCode = serviceCode;
	}

	public void setMsgType(Integer msgType) {
		MsgType = msgType;
	}

	public void setOpType(Integer opType) {
		OpType = opType;
	}

	public void setMsgCoding(Integer msgCoding) {
		MsgCoding = msgCoding;
	}

	public void setUssdContent(String ussdContent) {
		UssdContent = ussdContent;
	}

	public void setNetwork(Integer network) {
		Network = network;
	}

    public String getSessionId() {
        return SessionId;
    }

    public String getCpId() {
        return CpId;
    }

    public String getCpPassword() {
        return CpPassword;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public String getServiceCode() {
        return ServiceCode;
    }

    public Integer getMsgType() {
        return MsgType;
    }

    public Integer getOpType() {
        return OpType;
    }

    public Integer getMsgCoding() {
        return MsgCoding;
    }

    public String getUssdContent() {
        return UssdContent;
    }

    public Integer getNetwork() {
        return Network;
    }
}
