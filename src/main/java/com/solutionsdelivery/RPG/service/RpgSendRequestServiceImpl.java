package com.solutionsdelivery.RPG.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.service.OtpSendRequestServiceImpl;
import com.solutionsdelivery.RPG.dao.*;
import com.solutionsdelivery.RPG.dto.*;
import com.solutionsdelivery.directdebit.service.DirectDebitSendRequestServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("rpgSendRequestService")
public class RpgSendRequestServiceImpl implements RpgSendRequestService {

    private final RpgCredentials rpgCredentials;
    private ObjectMapper mapper = new ObjectMapper();
    private DirectDebitSendRequestServiceImpl directDebitSendRequestService;
    private OtpSendRequestServiceImpl otpSendRequestService;

    @Autowired
    public RpgSendRequestServiceImpl(RpgCredentials rpgCredentials, DirectDebitSendRequestServiceImpl directDebitSendRequestService, OtpSendRequestServiceImpl otpSendRequestService) {
        this.rpgCredentials = rpgCredentials;
        this.directDebitSendRequestService = directDebitSendRequestService;
        this.otpSendRequestService = otpSendRequestService;
    }

    private SinglePaymentResponse singlePayment(String url, SinglePayment singlePayment) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(singlePayment, otpSendRequestService.createRequestOtpHeader());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, SinglePaymentResponse.class);
    }

    private BulkPaymentResponse bulkPayment(String url, BulkPayment bulkPayment) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(bulkPayment, otpSendRequestService.createRequestOtpHeader());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, BulkPaymentResponse.class);
    }

    private PaymentStatusResponse paymentStatus(String url, PaymentStatus paymentStatus) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(paymentStatus, otpSendRequestService.createRequestOtpHeader());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, PaymentStatusResponse.class);
    }

    private AccountEnquiryResponse accountEnquiry(String url, RequestOtp accountEnqury) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(accountEnqury, otpSendRequestService.createRequestOtpHeader());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, AccountEnquiryResponse.class);
    }

    private GetBanksResponse getActiveBanks(String url) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(otpSendRequestService.createRequestOtpHeader());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, GetBanksResponse.class);
    }

    @Override
    public SinglePaymentResponse singlePayment(SinglePayment singlePayment) throws Exception {

        System.out.println("");
        System.out.println("Single payment request payload = [" + singlePayment.toString() + "]");
        System.out.println("");

        SinglePaymentResponse response =  singlePayment(rpgCredentials.getSinglePaymentLink(), singlePayment);

        System.out.println("");
        System.out.println("Single payment response payload = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public BulkPaymentResponse bulkPayment(BulkPayment bulkPayment) throws Exception {

        System.out.println("");
        System.out.println("bulk payment request payload = [" + bulkPayment.toString() + "]");
        System.out.println("");

        BulkPaymentResponse response =  bulkPayment(rpgCredentials.getBulkPaymentLink(), bulkPayment);

        System.out.println("");
        System.out.println("bulk payment response payload = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public PaymentStatusResponse paymentStatus(PaymentStatus paymentStatus) throws Exception {

        System.out.println("");
        System.out.println("payment status request payload = [" + paymentStatus.toString() + "]");
        System.out.println("");

        PaymentStatusResponse response =  paymentStatus(rpgCredentials.getBulkPaymentLink(), paymentStatus);

        System.out.println("");
        System.out.println("payment status response payload = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public AccountEnquiryResponse accountEnquiry(RequestOtp accountEnquiry) throws Exception {

        System.out.println("");
        System.out.println("account enquiry request payload = [" + accountEnquiry.toString() + "]");
        System.out.println("");

        AccountEnquiryResponse response =  accountEnquiry(rpgCredentials.getBulkPaymentLink(), accountEnquiry);

        System.out.println("");
        System.out.println("account enquiry response payload = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public GetBanksResponse getActiveBanks() throws Exception {

        GetBanksResponse response =  getActiveBanks(rpgCredentials.getBulkPaymentLink());

        System.out.println("");
        System.out.println("get active banks response payload = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }
}
