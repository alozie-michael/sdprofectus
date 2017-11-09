package com.remita.ussd.controller;

import com.remita.ussd.dao.*;
import com.remita.ussd.service.ProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PullRequestController {

    @Autowired
    ProcessRequestService processRequestService;

    @RequestMapping(value = "/pull_ussd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PullResponse> pullRequest(@RequestBody PullRequest pullRequest) {

        PullResponse pullResponse = new PullResponse();

        System.out.println("current Thread = [" + Thread.currentThread().getName() + "]");
        try {
            processRequestService.processRequest(pullRequest);
        } catch (Exception e) {

        }


        pullResponse.setSessionId(pullRequest.getSessionId());
        pullResponse.setMsisdn(pullRequest.getMsisdn());
        pullResponse.setErrorCode("200");
        pullResponse.setErrorMsg("success");

        System.out.println("pull Response = [" + pullResponse.toString() + "]");

        return new ResponseEntity<>(pullResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "U_Abort", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PullResponse> abortRequest(@RequestBody AbortRequest abortRequest) {

        PullResponse pullResponse = new PullResponse();

        try {
            processRequestService.abortSession(abortRequest);
        } catch (Exception e) {

            e.printStackTrace();
        }

        pullResponse.setSessionId(abortRequest.getSessionID());
        pullResponse.setMsisdn(abortRequest.getMsisdn());
        pullResponse.setErrorCode("200");
        pullResponse.setErrorMsg("success");

        return new ResponseEntity<>(pullResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "push_sms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PushSMSResponse> pushSMSRequest(@RequestBody PushSMSRequest pushSMSRequest) {

        PushSMSResponse pushSMSResponse = new PushSMSResponse();

        Thread process = new Thread(() -> {
            try {

                processRequestService.processSMSRequest(pushSMSRequest);

            } catch (Exception e) {

                e.printStackTrace();
            }

        });

        process.start();

        pushSMSResponse.setSessionId(pushSMSRequest.getSessionId());
        pushSMSResponse.setMsisdn(pushSMSRequest.getMsisdn());
        pushSMSResponse.setErrorCode("200");
        pushSMSResponse.setErrorMsg("success");

        return new ResponseEntity<>(pushSMSResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "mtb_report", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MtbReportResponse> mtbReport(@RequestBody MtbReportRequest mtbReportRequest) {

        MtbReportResponse mtbReportResponse = new MtbReportResponse();

        Thread process = new Thread(() -> {
            try {

                processRequestService.processMtbReport(mtbReportRequest);

            } catch (Exception e) {

                e.printStackTrace();
            }

        });

        process.start();

        mtbReportResponse.setSessionId(mtbReportRequest.getSessionId());
        mtbReportResponse.setMsisdn(mtbReportRequest.getMsisdn());
        mtbReportResponse.setErrorCode("200");
        mtbReportResponse.setErrorMsg("success");

        return new ResponseEntity<>(mtbReportResponse, HttpStatus.OK);
    }

}
