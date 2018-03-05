package com.solutionsdelivery.OTP.service;

import com.solutionsdelivery.OTP.dao.AuthParams;
import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.OtpRequestLogsResponse;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.RequestResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.OTP.model.AccountNumber;
import com.solutionsdelivery.OTP.model.Data;
import com.solutionsdelivery.OTP.model.GeneralOtpRequestLogs;
import com.solutionsdelivery.OTP.model.OtpRequestLogs;
import com.solutionsdelivery.OTP.repository.AccountNumberRepository;
import com.solutionsdelivery.OTP.repository.GeneralOtpRequestLogsRepository;
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
    private final GeneralOtpRequestLogsRepository generalOtpRequestLogsRepository;
    private final BankRepository bankRepository;
    private final AccountNumberRepository accountNumberRepository;

    private Encryptor encryptor = new Encryptor();

    @Autowired
    public OtpProcessRequestServiceImpl(OtpSendRequestService otpSendRequestService, OtpRequestLogsRepository otpRequestLogsRepository, BankRepository bankRepository, AccountNumberRepository accountNumberRepository, GeneralOtpRequestLogsRepository generalOtpRequestLogsRepository) {
        this.otpRequestLogsRepository = otpRequestLogsRepository;
        this.otpSendRequestService = otpSendRequestService;
        this.bankRepository = bankRepository;
        this.accountNumberRepository = accountNumberRepository;
        this.generalOtpRequestLogsRepository = generalOtpRequestLogsRepository;
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
        List<com.solutionsdelivery.OTP.dto.OtpRequestLogs> otpRequestLogsList = new ArrayList<>();
        List<OtpRequestLogs> otpRequestLogs = otpRequestLogsRepository.findAll();

        if(otpRequestLogs == null){
            otpRequestLogsResponse.setResponseCode("01");
            otpRequestLogsResponse.setResponseMessage("no logs found!");
        }else {

            for(OtpRequestLogs otpRequestLogs1 : otpRequestLogs){

                List<RequestResponse> requestResponses = new ArrayList<>();
                com.solutionsdelivery.OTP.dto.OtpRequestLogs otpRequestLogs2 = new com.solutionsdelivery.OTP.dto.OtpRequestLogs();
                List<Data> data = otpRequestLogs1.getData();

                for(Data data1: data){
                    RequestResponse requestResponse = new RequestResponse();
                    BeanUtils.copyProperties(data1, requestResponse);

                    requestResponses.add(requestResponse);
                }

                otpRequestLogs2.setStartTime(otpRequestLogs1.getStartTime());
                otpRequestLogs2.setRequestResponses(requestResponses);

                otpRequestLogsList.add(otpRequestLogs2);

            }

            otpRequestLogsResponse.setResponseCode("00");
            otpRequestLogsResponse.setResponseMessage("successful");
            otpRequestLogsResponse.setOtpRequestLogs(otpRequestLogsList);

        }

        return otpRequestLogsResponse;
    }

    @Override
    public OtpRequestLogsResponse getOtpRequestLog(String startTime) {

        OtpRequestLogsResponse otpRequestLogsResponse = new OtpRequestLogsResponse();
        List<com.solutionsdelivery.OTP.dto.OtpRequestLogs> otpRequestLogs = new ArrayList<>();
        OtpRequestLogs otpRequestLog = otpRequestLogsRepository.findByStartTimeContaining(startTime);

        if(otpRequestLog == null){
            otpRequestLogsResponse.setResponseCode("01");
            otpRequestLogsResponse.setResponseMessage("No record found");
        }else {

            com.solutionsdelivery.OTP.dto.OtpRequestLogs otpRequestLogs1 = new com.solutionsdelivery.OTP.dto.OtpRequestLogs();
            List<RequestResponse> requestResponses = new ArrayList<>();

            List<Data> data = otpRequestLog.getData();

            for(Data data1: data){
                RequestResponse requestResponse = new RequestResponse();

                BeanUtils.copyProperties(data1, requestResponse);
                requestResponses.add(requestResponse);
            }

            otpRequestLogs1.setStartTime(otpRequestLog.getStartTime());
            otpRequestLogs1.setRequestResponses(requestResponses);
            otpRequestLogs.add(otpRequestLogs1);

            otpRequestLogsResponse.setResponseMessage("successful");
            otpRequestLogsResponse.setResponseCode("00");
            otpRequestLogsResponse.setOtpRequestLogs(otpRequestLogs);

        }

        return otpRequestLogsResponse;
    }

    /*
    *   Every 1 hour, send OTP to all account numbers in accountNumber entity and persist response from Remita to
    *   OtpRequestLogs entity.
    * */
    @Scheduled(cron = "0 0 0/1 * * MON-FRI")
    private void scheduledRequestOtp(){
        /*
        * Get account numbers from DB
        * */
        List<AccountNumber> accountNumbers = accountNumberRepository.findAll();
        OtpRequestLogs otpRequestLogs = new OtpRequestLogs();
        List<Data> dataList = new ArrayList<>();
        String bankCode;
        String accountNumber = "";

        String startTime = getTime();

        assert accountNumbers != null;
        for (AccountNumber newAccountNumber: accountNumbers){

            bankCode = newAccountNumber.getBankCode();
            accountNumber = newAccountNumber.getAccountNumber();
            Bank bank = bankRepository.findByBankCodeContaining(bankCode);

            RequestOtp requestOtp = new RequestOtp();
            requestOtp.setBankCode(encryptor.encrypt(bankCode));
            requestOtp.setAccountNo(encryptor.encrypt(accountNumber));

            try {

                otpRequestLogs.setStartTime(startTime);

                Data data = new Data();
                data.setBank(bank.getBankName());
                data.setAccountNumber(accountNumber);
                data.setRequest("{'bankCode' : " + bankCode + ", 'accountNumber' : " + accountNumber + "}");
                data.setRequestTimeStamp(getTimeStamp());
                data.setStatus("no response");
                data.setOtpRequestLogs(otpRequestLogs);
                dataList.add(data);
                otpRequestLogs.setData(dataList);


                RequestOtpResponse requestOtpResponse = otpSendRequestService.requestOtp(requestOtp);

                dataList.remove(data);

                data.setResponseTimeStamp(getTimeStamp());
                data.setResponse(requestOtpResponse.toString());
                data.setStatus((requestOtpResponse.getStatus() != null)? requestOtpResponse.getStatus() : "no response");
                data.setOtpRequestLogs(otpRequestLogs);

                dataList.add(data);

                otpRequestLogs.setData(dataList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        log.info("saving otp request result to database");
        otpRequestLogsRepository.save(otpRequestLogs);
        log.info("otp request result saved to database", accountNumber);
    }

    /*
    * every 24hours, move all data from OtpRequestLogs entity to GeneralOtpRequestLogs entity,
    * then truncate OtpRequestLogs entity
    * */
    @Scheduled(cron = "0 0 0/24 * * MON-FRI")
    private void switchTables(){
        /*
        * Get account numbers from DB
        * */
        List<OtpRequestLogs> otpRequestLogs = otpRequestLogsRepository.findAll();

        for(OtpRequestLogs otpRequestLogs1: otpRequestLogs){
            GeneralOtpRequestLogs generalOtpRequestLogs = new GeneralOtpRequestLogs();
            List<Data> data = otpRequestLogs1.getData();

            for(Data data1: data){
                BeanUtils.copyProperties(data1, generalOtpRequestLogs);
                generalOtpRequestLogsRepository.save(generalOtpRequestLogs);
            }
        }

        otpRequestLogsRepository.deleteAll();

    }

    private String getTimeStamp(){
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }
    private String getTime(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

}
