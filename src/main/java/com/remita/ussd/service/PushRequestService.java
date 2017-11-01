package com.remita.ussd.service;

import com.remita.ussd.dao.PushRequest;
import com.remita.ussd.dao.PushResponse;
import com.remita.ussd.dao.PushSMSRequest;
import com.remita.ussd.dao.PushSMSResponse;

public interface PushRequestService {
	
	PushResponse pushRequest(PushRequest pushRequest) throws Exception;

}
