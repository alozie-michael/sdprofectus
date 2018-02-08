package com.solutionsdelivery.OTP.service;

import com.solutionsdelivery.OTP.dao.AuthParams;
import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;

import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;

import com.solutionsdelivery.RPG.dao.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("otpProcessRequestService")
public class OtpProcessRequestServiceImpl implements OtpProcessRequestService {

    private final
    OtpSendRequestService otpSendRequestService;

    private Encryptor encryptor = new Encryptor();

    @Autowired
    public OtpProcessRequestServiceImpl(OtpSendRequestService otpSendRequestService) {
        this.otpSendRequestService = otpSendRequestService;
    }

    @Override
    public RequestOtpResponse requestOtp(RequestOtp requestOtp) throws Exception {

        RequestOtp requestOtp1 = new RequestOtp();
        requestOtp1.setAccountNo(encryptor.encrypt(requestOtp.getAccountNo()));
        requestOtp1.setBankCode(encryptor.encrypt(requestOtp.getBankCode()));

        return otpSendRequestService.requestOtp(requestOtp1);
    }

    @Override
    public ValidateOtpResponse validateOtp(ValidateOTP validateOTP) throws Exception {

        List<AuthParams> newAuthParams = new ArrayList<>();
        List<AuthParams> authParams = validateOTP.getAuthParams();

        for(AuthParams authParam : authParams){
            AuthParams authParams2 = new AuthParams();
            authParams2.setParam1(encryptor.encrypt(authParam.getParam1()));
            authParams2.setValue(encryptor.encrypt(authParam.getValue()));

            newAuthParams.add(authParams2);
        }

        ValidateOTP validateOTP1 = new ValidateOTP();
        validateOTP1.setRemitaTransRef(encryptor.encrypt(validateOTP.getRemitaTransRef()));
        validateOTP1.setAuthParams(newAuthParams);

        return otpSendRequestService.validateOtp(validateOTP1);
    }


}
