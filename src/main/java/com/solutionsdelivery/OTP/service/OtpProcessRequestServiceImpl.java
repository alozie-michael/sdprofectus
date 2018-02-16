package com.solutionsdelivery.OTP.service;

import com.solutionsdelivery.OTP.dao.AuthParams;
import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.OtpRequestLogsResponse;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.OTP.model.AccountNumber;
import com.solutionsdelivery.OTP.model.OtpRequestLogs;
import com.solutionsdelivery.OTP.repository.AccountNumberRepository;
import com.solutionsdelivery.OTP.repository.OtpRequestLogsRepository;
import com.solutionsdelivery.RPG.dao.Encryptor;
import com.solutionsdelivery.directdebit.model.Bank;
import com.solutionsdelivery.directdebit.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("otpProcessRequestService")
@Slf4j
public class OtpProcessRequestServiceImpl implements OtpProcessRequestService {

    private final OtpSendRequestService otpSendRequestService;
    private final OtpRequestLogsRepository otpRequestLogsRepository;
    private final BankRepository bankRepository;
    private final AccountNumberRepository accountNumberRepository;

    private Encryptor encryptor = new Encryptor();

    @Autowired
    public OtpProcessRequestServiceImpl(OtpSendRequestService otpSendRequestService, OtpRequestLogsRepository otpRequestLogsRepository, BankRepository bankRepository, AccountNumberRepository accountNumberRepository) {
        this.otpRequestLogsRepository = otpRequestLogsRepository;
        this.otpSendRequestService = otpSendRequestService;
        this.bankRepository = bankRepository;
        this.accountNumberRepository = accountNumberRepository;
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

    @Override
    public OtpRequestLogsResponse getOtpRequestLogs() {

        OtpRequestLogsResponse otpRequestLogsResponse = new OtpRequestLogsResponse();
        List<com.solutionsdelivery.OTP.dto.OtpRequestLogs> newOtpRequestLogs = new ArrayList<>();
        List<OtpRequestLogs> otpRequestLogs = otpRequestLogsRepository.findAll();

        if(otpRequestLogs == null){
            otpRequestLogsResponse.setResponseCode("01");
            otpRequestLogsResponse.setResponseMessage("no logs found!");
        }else {

            for(OtpRequestLogs otpRequestLogs2 : otpRequestLogs){
                com.solutionsdelivery.OTP.dto.OtpRequestLogs otpRequestLogs1 = new com.solutionsdelivery.OTP.dto.OtpRequestLogs();
                BeanUtils.copyProperties(otpRequestLogs2, otpRequestLogs1);

                newOtpRequestLogs.add(otpRequestLogs1);
            }

            otpRequestLogsResponse.setResponseCode("00");
            otpRequestLogsResponse.setResponseMessage("successful");
            otpRequestLogsResponse.setOtpRequestLogs(newOtpRequestLogs);

        }

        return otpRequestLogsResponse;
    }

    @Scheduled(cron = "0 0 0/1 * * MON-FRI")
    public void scheduledRequestOtp(){
        /*
        * Get account numbers from DB
        * */
        List<AccountNumber> accountNumbers = accountNumberRepository.findAll();

        assert accountNumbers != null;
        for (AccountNumber newAccountNumber: accountNumbers){

            String bankCode = newAccountNumber.getBankCode();
            String accountNumber = newAccountNumber.getAccountNumber();

            OtpRequestLogs otpRequestLogs = new OtpRequestLogs();
            RequestOtp requestOtp = new RequestOtp();
            Bank bank = bankRepository.findByBankCodeContaining(bankCode);
            requestOtp.setBankCode(encryptor.encrypt(bankCode));
            requestOtp.setAccountNo(encryptor.encrypt(accountNumber));

            try {

                String requestTimeStamp = getTimeStamp();
                otpRequestLogs.setRequestTimeStamp(requestTimeStamp);

                RequestOtpResponse requestOtpResponse = otpSendRequestService.requestOtp(requestOtp);

                String responseTimeStamp = getTimeStamp();
                otpRequestLogs.setResponseTimeStamp(responseTimeStamp);
                otpRequestLogs.setRequest("{'bankCode' : " + bankCode + ", 'accountNumber' : " + accountNumber + "}");
                otpRequestLogs.setResponse(requestOtpResponse.toString());
                otpRequestLogs.setBank(bank.getBankName());
                otpRequestLogs.setAccountNumber(accountNumber);

                log.info("saving otp request log for account number {} to database", accountNumber);
                otpRequestLogsRepository.save(otpRequestLogs);
                log.info("otp request log saved for account number {} to database", accountNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private String getTimeStamp(){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

}
