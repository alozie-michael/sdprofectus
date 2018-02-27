package com.solutionsdelivery.OTP.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutionsdelivery.OTP.dao.OtpCredentials;
import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.directdebit.dao.Hash512Class;
import com.solutionsdelivery.directdebit.service.DirectDebitSendRequestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("otpSendRequestService")
@Slf4j
public class OtpSendRequestServiceImpl implements OtpSendRequestService {

    private final OtpCredentials otpCredentials;
    private ObjectMapper mapper = new ObjectMapper();
    private Hash512Class hash512Class = new Hash512Class();

    private DirectDebitSendRequestServiceImpl directDebitSendRequestService;

    @Autowired
    public OtpSendRequestServiceImpl(OtpCredentials otpCredentials, DirectDebitSendRequestServiceImpl directDebitSendRequestService) {
        this.otpCredentials = otpCredentials;
        this.directDebitSendRequestService = directDebitSendRequestService;
    }


    private RequestOtpResponse requestOtp(String url, RequestOtp requestOtp) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(requestOtp, createRequestOtpHeader());
        log.info("sending OTP request to Remita - {}", requestObject.toString());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("OTP request response from Remita - {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, RequestOtpResponse.class);
    }

    private ValidateOtpResponse validateOtp(String url, ValidateOTP validateOTP) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(validateOTP, createRequestOtpHeader());
        log.info("sending OTP validation to Remita - {}", requestObject.toString());
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("OTP validation response from Remita - {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, ValidateOtpResponse.class);
    }


    public HttpHeaders createRequestOtpHeader() {

        String requestTimeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
        String requestId = getTimeStamp();
        String apiDetailsHash = hash512Class.getResponseHash(otpCredentials.getApiKey() + requestId + otpCredentials.getApiToken());

        HttpHeaders headers = new HttpHeaders();
        headers.add("MERCHANT_ID", otpCredentials.getMerchantId());
        headers.add("API_KEY", otpCredentials.getApiKey());
        headers.add("REQUEST_ID", requestId);
        headers.add("REQUEST_TS", requestTimeStamp + "+0000");
        headers.add("API_DETAILS_HASH",apiDetailsHash);
        return headers;
    }

    @Override
    public RequestOtpResponse requestOtp(RequestOtp requestOtp) throws Exception {
        String url = otpCredentials.getRequestOTPLink();
        return requestOtp(url, requestOtp);
    }

    @Override
    public ValidateOtpResponse validateOtp(ValidateOTP validateOTP) throws Exception {
        String url = otpCredentials.getValidateOTPLink();
        return validateOtp(url, validateOTP);
    }

    private String getTimeStamp(){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(new Date());
    }

}
