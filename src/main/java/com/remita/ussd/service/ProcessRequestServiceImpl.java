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

        System.out.println("current Thread = [" + Thread.currentThread().getName() + "]");

        PushRequest newRequest = new PushRequest();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new java.util.Date());
        String cpPassword = DigestUtils.md5Hex(pullRequest.getCpId() + 123123 + timeStamp);

        BeanUtils.copyProperties(pullRequest, newRequest);

        newRequest.setTimeStamp(timeStamp);
        newRequest.setCpPassword(cpPassword);
        newRequest.setOpType(1);

        if (pullRequest.getMsgType().equals(0) || pullRequest.getUssdContent().equals("0")) {

            newRequest.setMsgType(1);

            map.remove(pullRequest.getSessionId());

            newRequest.setUssdContent(" Welcome to Remita \n\n 1> Transfer \n 2> Airtime \n 3> Balance \n 4> Pay TSA and Billers \n 5> Get loan \n 6> RRR \n 7> Receipt \n 8> Register \n\n 9> Next ");

            return pushRequestService.pushRequest(newRequest);

        } else if (pullRequest.getMsgType().equals(1)) {

            Integer action = 1;

            try {
                action = Integer.valueOf(pullRequest.getUssdContent());
            }catch (Exception e){
                System.out.println(e);
            }

            Operation getOperation;

            if (map.containsKey(pullRequest.getSessionId())) {

                getOperation = map.get(pullRequest.getSessionId());
                getOperation.setStep(getOperation.getStep() + 1);

            } else {
                getOperation = new Operation();
                getOperation.setStep(1);
                getOperation.setAction(action);
                map.put(pullRequest.getSessionId(), getOperation);
            }


            switch (getOperation.getAction()) {
                case 1:
                    this.processTransfer(newRequest, getOperation.getStep());
                    break;
                case 2:
                    this.processAirtime(newRequest, getOperation.getStep());
                    break;
                case 3:
                    this.processBalance(newRequest, getOperation.getStep());
                    break;
                case 4:
                    this.processPayTSAAndBillers(newRequest, getOperation.getStep());
                    break;
                case 5:
                    this.processGetLoan(newRequest, getOperation.getStep());
                    break;
                case 6:
                    this.processRRR(newRequest, getOperation.getStep());
                    break;
                case 7:
                    this.processReceipt(newRequest, getOperation.getStep());
                    break;
                case 8:
                    this.processRegistration(newRequest, getOperation.getStep());
                    break;
                default:
                    newRequest.setUssdContent(" Welcome to Remita \n\n Invalid response. \n\n 0> Back ");
                    break;
            }
            return pushRequestService.pushRequest(newRequest);
        }

        return null;
    }

    @Async
    public void abortSession(AbortRequest abortRequest) {

        map.remove(abortRequest.getSessionID());
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
