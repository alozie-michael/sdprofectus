package com.remita.ussd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.remita.ussd.dao.*;
import com.remita.ussd.model.Activities;
import com.remita.ussd.object.Menus;
import com.remita.ussd.object.Operation;
import com.remita.ussd.repository.ActivitiesRepository;
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

    @Autowired
    ActivitiesRepository activitiesRepository;


    Map<String, Operation> map = new ConcurrentHashMap<>();

    @Async
    public PushResponse processRequest(PullRequest pullRequest) throws Exception {

        //persist activities to DB
        Activities activities = persistActivity(pullRequest);
        activitiesRepository.save(activities);

        PushRequest newRequest = new PushRequest();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
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

            Integer action;

            try {
                action = Integer.valueOf(pullRequest.getUssdContent());
            }catch (Exception e){
                action = 10;
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
                    this.processTransfer(newRequest, getOperation, pullRequest.getUssdContent());
                    break;
                case 2:
                    this.processAirtime(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
                    break;
                case 3:
                    this.processBalance(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
                    break;
                case 4:
                    this.processPayTSAAndBillers(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
                    break;
                case 5:
                    this.processGetLoan(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
                    break;
                case 6:
                    this.processRRR(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
                    break;
                case 7:
                    this.processReceipt(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
                    break;
                case 8:
                    this.processRegistration(newRequest, getOperation.getStep(), pullRequest.getUssdContent());
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



    public Activities persistActivity(PullRequest pullRequest){

        String network;

        switch (pullRequest.getNetwork()){
            case 1:
                network = "MTN";
                break;
            case 2:
                network = "9MOBILE";
                break;
            case 3:
                network = "Airtel";
                break;
            case 4:
                network = "Globacom";
                break;
            default:
                network = "invalid network";
                break;
        }

        String createdDate = new SimpleDateFormat("yyyy/MM/dd HH.ss").format(new Date());
        Activities activities = new Activities();

        activities.setTimestamp(pullRequest.getTimeStamp());
        activities.setSessionID(pullRequest.getSessionId());
        activities.setMsisdn(pullRequest.getMsisdn());
        activities.setServiceCode(pullRequest.getServiceCode());
        activities.setNetwork(network);
        activities.setCreatedDate(createdDate);

        return activities;
    }
}
