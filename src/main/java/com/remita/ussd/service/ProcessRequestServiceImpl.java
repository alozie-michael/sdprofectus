package com.remita.ussd.service;

import java.text.SimpleDateFormat;

import com.remita.ussd.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("processRequestService")
public class ProcessRequestServiceImpl implements ProcessRequestService{

	@Autowired
	PushRequestService pushRequestService;

	@Autowired
    PushSMSRequestService pushSMSRequestService;
	
	public PushResponse processRequest(PullRequest pullRequest) throws Exception {

        PushRequest newRequest = new PushRequest();

		String  timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new java.util.Date());

		if(pullRequest.getMsgType().equals(0)){
			

			newRequest.setTimeStamp(timeStamp);
			newRequest.setSessionId(pullRequest.getSessionId());
			newRequest.setCpPassword(pullRequest.getCpPassword());
			newRequest.setMSISDN(pullRequest.getMSISDN());
			newRequest.setServiceCode(pullRequest.getServiceCode());
			newRequest.setMsgType(1);
			newRequest.setOpType(1);
			newRequest.setMsgCoding(68);
			newRequest.setUssdContent("1.Query Balance 2.Query Trans");
			
			return pushRequestService.pushRequest(newRequest);
			
		}else if(pullRequest.getMsgType().equals(1)){

		    if(pullRequest.getUssdContent().equals("2")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpPassword(pullRequest.getCpPassword());
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent("Trans is xxx. 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("1")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpPassword(pullRequest.getCpPassword());
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent("Your balance is #30,000.00. 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else {

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpPassword(pullRequest.getCpPassword());
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent("1.Query Balance 2.Query Trans");

                return pushRequestService.pushRequest(newRequest);

            }

			
		}else {

            return null;
		}
		
	}

    @Override
    public void abortSession(AbortRequest abortRequest) {
        //abort session logic
    }

    @Override
    public PushSMSResponse processSMSRequest(PushSMSRequest pushSMSRequest) throws Exception {
        return pushSMSRequestService.pushSMSRequest(pushSMSRequest);
    }

    @Override
    public MtbReportResponse processMtbReport(MtbReportRequest mtbReportRequest) {
        return null;
    }
}
