package com.remita.ussd.service;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.remita.ussd.dao.*;
import com.remita.ussd.object.Menus;
import com.remita.ussd.object.Operation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("processRequestService")
public class ProcessRequestServiceImpl extends Menus implements ProcessRequestService {

    @Autowired
    PushRequestService pushRequestService;

    @Autowired
    PushSMSRequestService pushSMSRequestService;

    Map<String, Operation> map = new ConcurrentHashMap<>();

    @Async
    public PushResponse processRequest(PullRequest pullRequest) throws Exception {

        PushRequest newRequest = new PushRequest();


        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new java.util.Date());
        String cpPassword = DigestUtils.md5Hex(pullRequest.getCpId() + 123123 + timeStamp);

        BeanUtils.copyProperties(pullRequest, newRequest);


        newRequest.setTimeStamp(timeStamp);
        newRequest.setCpPassword(cpPassword);
        newRequest.setOpType(1);

        if (pullRequest.getMsgType().equals(0)) {
            newRequest.setMsgType(1);


            newRequest.setUssdContent(" Welcome to Remita \n\n 1> Transfer \n 2> Airtime \n 3> Balance \n 4> Pay TSA and Billers \n 5> Get loan \n 6> RRR \n 7> Receipt \n 8> Register \n\n 9> Next ");

            return pushRequestService.pushRequest(newRequest);

        } else if (pullRequest.getMsgType().equals(1)) {

            Operation getOperation;

            if (map.containsKey(pullRequest.getSessionId())) {

                getOperation = map.get(pullRequest.getSessionId());
                getOperation.setStep(getOperation.getStep() + 1);

            } else {
                getOperation = new Operation();
                getOperation.setStep(1);
                getOperation.setAction(Integer.valueOf(pullRequest.getUssdContent()));
                map.put(pullRequest.getSessionId(), getOperation);
            }


            switch (getOperation.getAction()) {
                case 1:
                    this.processTransfer(newRequest, getOperation.getStep());
                    break;

            }
            return pushRequestService.pushRequest(newRequest);
        }
            /*if (getOperation.getAction() == 1) {


            } else if (pullRequest.getUssdContent().equals("2")) {

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

            } else if (pullRequest.getUssdContent().equals("3")) {

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

            } else if (pullRequest.getUssdContent().equals("4")) {

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

            } else if (pullRequest.getUssdContent().equals("5")) {

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

            } else if (pullRequest.getUssdContent().equals("6")) {

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

            } else if (pullRequest.getUssdContent().equals("7")) {

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

            } else if (pullRequest.getUssdContent().equals("8")) {

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

            } else {

                newRequest.setTimeStamp(timeStamp);
                newRequest.setSessionId(pullRequest.getSessionId());
                newRequest.setCpId(pullRequest.getCpId());
                newRequest.setCpPassword(cpPassword);
                newRequest.setMSISDN(pullRequest.getMSISDN());
                newRequest.setServiceCode(pullRequest.getServiceCode());
                newRequest.setMsgType(1);
                newRequest.setOpType(1);
                newRequest.setMsgCoding(68);
                newRequest.setUssdContent(" Welcome to Remita \n\n 1> Transfer \n 2> Airtime \n 3> Balance \n 4> Pay TSA and Billers \n 5> Get loan \n 6> RRR \n 7> Receipt \n 8> Register \n\n 9> Next ");

                return pushRequestService.pushRequest(newRequest);

            }


        } else {

            return null;
        }*/
        return null;
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
