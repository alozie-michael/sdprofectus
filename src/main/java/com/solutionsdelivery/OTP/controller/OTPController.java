package com.solutionsdelivery.OTP.controller;

import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.OTP.service.OtpProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/remita/OTP/")
public class OTPController {

    private final OtpProcessRequestService OtpProcessRequestService;

    @Autowired
    public OTPController(OtpProcessRequestService OtpProcessRequestService) {
        this.OtpProcessRequestService = OtpProcessRequestService;
    }


    @RequestMapping(value = "requestOtp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestOtpResponse> requestOtp(@RequestBody RequestOtp requestOtp) {

        RequestOtpResponse requestOtpResponse = new RequestOtpResponse();

        try {

            requestOtpResponse = OtpProcessRequestService.requestOtp(requestOtp);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(requestOtpResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "validateOTP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidateOtpResponse> validateOtp(@RequestBody ValidateOTP validateOTP) {

        ValidateOtpResponse validateOtpResponse = new ValidateOtpResponse();

        try {

            validateOtpResponse = OtpProcessRequestService.validateOtp(validateOTP);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(validateOtpResponse, HttpStatus.OK);
    }

}
