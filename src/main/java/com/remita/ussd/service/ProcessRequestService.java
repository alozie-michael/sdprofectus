package com.remita.ussd.service;

import com.remita.ussd.dao.*;

public interface ProcessRequestService {
	
	PushResponse processRequest(PullRequest pullRequest) throws Exception;
	void abortSession(AbortRequest abortRequest);
	PushSMSResponse processSMSRequest(PushSMSRequest pushSMSRequest) throws Exception;
    MtbReportResponse processMtbReport(MtbReportRequest mtbReportRequest);

}
