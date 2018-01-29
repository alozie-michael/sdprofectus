package com.solutionsdelivery.RPG.service;


import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.OTP.service.OtpSendRequestService;
import com.solutionsdelivery.RPG.dao.*;
import com.solutionsdelivery.RPG.dto.*;
import com.solutionsdelivery.RPG.model.Account;
import com.solutionsdelivery.RPG.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("rpgProcessRequestService")
public class RpgProcessRequestServiceImpl implements RpgProcessRequestService {

    private Encryptor encryptor = new Encryptor();

    private final RpgSendRequestService rpgSendRequestService;
    private final OtpSendRequestService otpSendRequestService;
    private final RpgCredentials rpgCredentials;

    @Autowired
    public RpgProcessRequestServiceImpl(RpgSendRequestService rpgSendRequestService, RpgCredentials rpgCredentials, OtpSendRequestService otpSendRequestService) {
        this.rpgSendRequestService = rpgSendRequestService;
        this.rpgCredentials = rpgCredentials;
        this.otpSendRequestService = otpSendRequestService;
    }

    @Override
    public RequestOtpResponse requestOtp(RequestOtp requestOtp) throws Exception {

        return otpSendRequestService.requestOtp(requestOtp);
    }

    @Override
    public ValidateOtpResponse validateOtp(ValidateOTP validateOTP) throws Exception {

        return otpSendRequestService.validateOtp(validateOTP);
    }

    @Override
    public SinglePaymentResponse singlePayment(SinglePayment singlePayment) throws Exception {

        String requestTimeStamp = new SimpleDateFormat("yyyy.MM.ddHH:mm:ss").format(new Date());
        String requestId = requestTimeStamp + "SINGLEPAYMENT";

        singlePayment.setFromBank(encryptor.encrypt(rpgCredentials.getDebitBankCode()));
        singlePayment.setTransRef(encryptor.encrypt(requestId));
        singlePayment.setAmount(encryptor.encrypt(singlePayment.getAmount()));
        singlePayment.setBeneficiaryEmail(encryptor.encrypt(singlePayment.getBeneficiaryEmail()));
        singlePayment.setCreditAccount(encryptor.encrypt(singlePayment.getCreditAccount()));
        singlePayment.setDebitAccount(encryptor.encrypt(rpgCredentials.getDebitAccount()));
        singlePayment.setToBank(encryptor.encrypt(singlePayment.getToBank()));
        singlePayment.setNarration(encryptor.encrypt(singlePayment.getNarration()));

        return rpgSendRequestService.singlePayment(singlePayment);
    }

    @Override
    public BulkPaymentResponse bulkPayment(BulkPayment bulkPayment) throws Exception {

        BulkPaymentInfo bulkPaymentInfo = bulkPayment.getBulkPaymentInfo();
        String requestTimeStamp = new SimpleDateFormat("yyyy.MM.ddHH:mm:ss").format(new Date());
        String requestId = requestTimeStamp + "BULKPAYMENT";

        //singlePayment.setFromBank(account.getBankCode());
        //singlePayment.setTransRef(requestId);

        return rpgSendRequestService.bulkPayment(bulkPayment);
    }

    @Override
    public PaymentStatusResponse paymentStatus(PaymentStatus paymentStatus) throws Exception {

        paymentStatus.setTransRef(encryptor.encrypt(paymentStatus.getTransRef()));

        return rpgSendRequestService.paymentStatus(paymentStatus);
    }

    @Override
    public AccountEnquiryResponse accountEnquiry(RequestOtp accountEnquiry) throws Exception {

        accountEnquiry.setAccountNo(encryptor.encrypt(accountEnquiry.getAccountNo()));
        accountEnquiry.setBankCode(encryptor.encrypt(accountEnquiry.getBankCode()));

        return rpgSendRequestService.accountEnquiry(accountEnquiry);
    }

    @Override
    public GetBanksResponse getBanks() throws Exception {
        return rpgSendRequestService.getActiveBanks();
    }
}
