package com.solutionsdelivery.RPG.controller;


import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.RPG.dao.*;
import com.solutionsdelivery.RPG.dto.*;
import com.solutionsdelivery.RPG.service.RpgProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/remita/RPG/")
public class RpgController {

    private final RpgProcessRequestService rpgProcessRequestService;

    @Autowired
    public RpgController(RpgProcessRequestService rpgProcessRequestService) {
        this.rpgProcessRequestService = rpgProcessRequestService;
    }


    @RequestMapping(value = "requestOtp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestOtpResponse> requestOtp(@RequestBody RequestOtp requestOtp) {

        RequestOtpResponse requestOtpResponse = new RequestOtpResponse();

        try {

            requestOtpResponse = rpgProcessRequestService.requestOtp(requestOtp);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(requestOtpResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "validateOTP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidateOtpResponse> validateOtp(@RequestBody ValidateOTP validateOTP) {

        ValidateOtpResponse validateOtpResponse = new ValidateOtpResponse();

        try {

            validateOtpResponse = rpgProcessRequestService.validateOtp(validateOTP);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(validateOtpResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "singlePayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SinglePaymentResponse> singlePayment(@RequestBody SinglePayment singlePayment) {

        SinglePaymentResponse singlePaymentResponse = new SinglePaymentResponse();

        try {

            singlePaymentResponse = rpgProcessRequestService.singlePayment(singlePayment);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(singlePaymentResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "bulkPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BulkPaymentResponse> bulkPayment(@RequestBody BulkPayment bulkPayment) {

        BulkPaymentResponse bulkPaymentResponse = new BulkPaymentResponse();

        try {

            bulkPaymentResponse = rpgProcessRequestService.bulkPayment(bulkPayment);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(bulkPaymentResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "paymentStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentStatusResponse> paymentStatus(@RequestBody PaymentStatus paymentStatus) {

        PaymentStatusResponse paymentStatusResponse = new PaymentStatusResponse();

        try {

            paymentStatusResponse = rpgProcessRequestService.paymentStatus(paymentStatus);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(paymentStatusResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "accountEnquiry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountEnquiryResponse> accountEnquiry(@RequestBody RequestOtp accountEnquiry) {

        AccountEnquiryResponse accountEnquiryResponse = new AccountEnquiryResponse();

        try {

            accountEnquiryResponse = rpgProcessRequestService.accountEnquiry(accountEnquiry);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(accountEnquiryResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "getActiveBanks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetBanksResponse> getActiveBanks() {

        GetBanksResponse getBanksResponse = new GetBanksResponse();

        try {

            getBanksResponse = rpgProcessRequestService.getBanks();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(getBanksResponse, HttpStatus.OK);
    }

}
