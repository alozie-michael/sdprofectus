package com.remita.ussd.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.remita.ussd.dao.*;
import com.remita.ussd.object.menus;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("processRequestService")
public class ProcessRequestServiceImpl extends menus implements ProcessRequestService{

	@Autowired
	PushRequestService pushRequestService;

	@Autowired
    PushSMSRequestService pushSMSRequestService;

    Map<String,Integer> map =new ConcurrentHashMap<>();
	
	public PushResponse processRequest(PullRequest pullRequest) throws Exception {

        PushRequest newRequest = new PushRequest();

		String  timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new java.util.Date());
        String cpPassword = DigestUtils.md5Hex(pullRequest.getCpId() + 123123 + timeStamp);


        if(pullRequest.getMsgType().equals(0)){

			newRequest.setTimeStamp(timeStamp);
			newRequest.setSessionId(pullRequest.getSessionId());
			newRequest.setCpId(pullRequest.getCpId());
			newRequest.setCpPassword(cpPassword);
			newRequest.setMSISDN(pullRequest.getMSISDN());
			newRequest.setServiceCode(pullRequest.getServiceCode());
			newRequest.setMsgType(1);
			newRequest.setOpType(1);
			newRequest.setMsgCoding(pullRequest.getMsgCoding());
			newRequest.setUssdContent(" Welcome to Remita \n\n 1> Transfer \n 2> Airtime \n 3> Balance \n 4> Pay TSA and Billers \n 5> Get loan \n 6> RRR \n 7> Receipt \n 8> Register \n\n 9> Next ");
			
			return pushRequestService.pushRequest(newRequest);
			
		}else if(pullRequest.getMsgType().equals(1)){

		    if(pullRequest.getUssdContent().equals("1")){

                map.put(pullRequest.getSessionId(), 1);

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);

                PushRequest request = processTransfer(newRequest, map.get(pullRequest.getSessionId()));

                map.remove(pullRequest.getSessionId());
                map.put(pullRequest.getSessionId(), 2);

                return pushRequestService.pushRequest(request);

            }else if (pullRequest.getUssdContent().equals("2")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - Airtime \n\n Not yet available. \n\n 0> Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("3")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - Balance \n\n Not yet available. \n\n 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("4")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - Pay TSA and Billers \n\n Not yet available. \n\n 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("5")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - Get Loan \n\n Not yet available. \n\n 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("6")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - RRR \n\n Not yet available. \n\n 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("7")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - Receipt \n\n Not yet available. \n\n 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else if (pullRequest.getUssdContent().equals("8")){

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Remita - Register \n\n Not yet available. \n\n 0. Back");

                return pushRequestService.pushRequest(newRequest);

            }else {

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Welcome to Remita \n\n 1> menus \n 2> Airtime \n 3> Balance \n 4> Pay TSA and Billers \n 5> Get loan \n 6> RRR \n 7> Receipt \n 8> Register \n\n 9> Next ");

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
