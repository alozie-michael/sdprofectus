package com.remita.directdebit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remita.directdebit.dao.*;
import com.remita.directdebit.dto.*;
import com.remita.directdebit.model.Mandate;
import com.remita.directdebit.repository.BankRepository;
import com.remita.directdebit.repository.DebitInstructionRepository;
import com.remita.directdebit.repository.MandateRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("sendRequestService")
public class SendRequestServiceImpl implements SendRequestService {

    private final
    MandateRepository mandateRepository;

    private final
    DebitInstructionRepository debitInstructionRepository;

    private  final BankRepository bankRepository;

    @Autowired
    private Credentials credentials;
    private ObjectMapper mapper = new ObjectMapper();
    private Hash512Class getHash = new Hash512Class();

    private String requestId;
    private String apiDetailsHash;

    @Autowired
    public SendRequestServiceImpl(MandateRepository mandateRepository, DebitInstructionRepository debitInstructionRepository, BankRepository bankRepository) {
        this.mandateRepository = mandateRepository;
        this.debitInstructionRepository = debitInstructionRepository;
        this.bankRepository = bankRepository;
    }

    private RequestOtpForMandateActivationResponse requestOtp(String url, RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(requestOtpForMandateActivation, createRequestOtpHeader());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, RequestOtpForMandateActivationResponse.class);
    }

    private MandateActivationResponse validateOtp(String url, MandateActivation mandateActivation) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(mandateActivation, createRequestOtpHeader());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, MandateActivationResponse.class);
    }

    private MandateSetupResponse mandateSetup(String url, MandateSetup mandateSetup) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(mandateSetup, createRequestHeader());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, MandateSetupResponse.class);
    }

    private StopMandateResponse stopMandate(String url, StopMandate stopMandate) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(stopMandate, createRequestHeader());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, StopMandateResponse.class);
    }

    private StopDebitResponse stopDebit(String url, StopDebit stopDebit) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(stopDebit, createRequestHeader());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, StopDebitResponse.class);
    }

    private MandateStatusResponse mandateStatus(String url) throws Exception {

        ResponseEntity<String> response = getResetTemplate().getForEntity(url, String.class);

        //convert from jsonp to json
        String editedBody = response.getBody();
        editedBody = StringUtils.strip(editedBody, "jsonp( )");

        return mapper.readValue(editedBody, MandateStatusResponse.class);
    }

    public DebitInstructionResponse debitInstruction(String url, DebitInstruction debitInstruction) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(debitInstruction, createRequestHeader());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, DebitInstructionResponse.class);
    }

    private HttpHeaders createRequestHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return headers;
    }

    private HttpHeaders createRequestOtpHeader() {

        String requestTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date());

        HttpHeaders headers = new HttpHeaders();
        headers.add("MERCHANT_ID", credentials.getMerchantId());
        headers.add("API_KEY", credentials.getApiKey());
        headers.add("REQUEST_ID", requestId);
        headers.add("REQUEST_TS", requestTimeStamp);
        headers.add("API_DETAILS_HASH",apiDetailsHash);
        return headers;
    }

    private RestTemplate getResetTemplate() {

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(messageConverter);

        System.setProperty("http.proxyHost", "192.9.200.10");
        System.setProperty("http.proxyPort", "3128");
        System.setProperty("http.nonProxyHosts", StringUtils.EMPTY);

        return restTemplate;
    }

    @Override
    public RequestOtpForMandateActivationResponse requestOtp(RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception {

        Mandate mandate = mandateRepository.findByMandateIdContaining(requestOtpForMandateActivation.getMandateId());

        requestId = mandate.getRequestId();
        String amount = mandate.getAmount();

        requestOtpForMandateActivation.setRequestId(requestId);

        String stringToHash = credentials.getMerchantId()+credentials.getServiceTypeId()+requestId+amount+credentials.getApiKey();
        apiDetailsHash = getHash.getResponseHash(stringToHash);

        System.out.println("");
        System.out.println("request OTP request payload = [" + requestOtpForMandateActivation.toString() + "]");
        System.out.println("");

        String url = credentials.getRequestOTPLink();

        RequestOtpForMandateActivationResponse response = requestOtp(url, requestOtpForMandateActivation);

        System.out.println("");
        System.out.println("request OTP response payload= [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public MandateActivationResponse validateOtp(MandateActivation mandateActivation) throws Exception {

        System.out.println("");
        System.out.println("validate OTP request payload = [" + mandateActivation.toString() + "]");
        System.out.println("");

        String url = credentials.getValidateOTPLink();
        MandateActivationResponse response = validateOtp(url, mandateActivation);

        System.out.println("");
        System.out.println("validate OTP response payload = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public MandateStatusResponse mandateStatus(MandateStatus mandateStatus) throws Exception {

        System.out.println("");
        System.out.println("Mandate status request = [" + mandateStatus.toString() + "]");
        System.out.println("");

        String url = credentials.getMandateStatusLink() + mandateStatus.getMerchantId() + "/" + mandateStatus.getRequestId() + "/" + mandateStatus.getHash() + "/status.reg";
        MandateStatusResponse response = mandateStatus(url);

        System.out.println("");
        System.out.println("Mandate status response = [" + response.toString() + "]");
        System.out.println("");

        return response;
    }

    @Override
    public DebitInstructionResponse debitInstruction(DebitInstruction debitInstruction) throws Exception {

        System.out.println("");
        System.out.println("debit instruction request = [" + debitInstruction.toString() + "]");
        System.out.println("");

        String url = credentials.getDebitInstructionLink();
        DebitInstructionResponse response = debitInstruction(url, debitInstruction);

        System.out.println("");
        System.out.println("debit instruction response = [" + response.toString() + "]");
        System.out.println("");

        if(response.getStatuscode().equals("069")){

            com.remita.directdebit.model.Bank bank = bankRepository.findByBankCodeContaining(debitInstruction.getFundingBankCode());
            com.remita.directdebit.model.DebitInstruction saveDebitInstruction = new com.remita.directdebit.model.DebitInstruction();

            BeanUtils.copyProperties(debitInstruction, saveDebitInstruction);

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss").format(new Date());
            saveDebitInstruction.setDateCreated(timeStamp);
            saveDebitInstruction.setFundingBank(bank.getBankName());
            saveDebitInstruction.setTransactionRef(response.getTransactionRef());
            saveDebitInstruction.setStatus("PROCESSING");


            Mandate mandate = mandateRepository.findByMandateIdContaining(response.getMandateId());
            List<com.remita.directdebit.model.DebitInstruction> debitInstructions = mandate.getDebitInstruction();

            if(debitInstructions == null) {

                List<com.remita.directdebit.model.DebitInstruction> debitInstructions1 = new ArrayList<>();

                saveDebitInstruction.setMandate(mandate);
                debitInstructions1.add(saveDebitInstruction);

                mandate.setDebitInstruction(debitInstructions1);

                debitInstructionRepository.save(saveDebitInstruction);

            }else {

                saveDebitInstruction.setMandate(mandate);
                debitInstructions.add(saveDebitInstruction);

                mandate.setDebitInstruction(debitInstructions);

                debitInstructionRepository.save(saveDebitInstruction);
            }

        }

        return response;
    }

    @Override
    public StopMandateResponse stopMandate(StopMandate stopMandate) throws Exception {

        System.out.println("");
        System.out.println("Stop Mandate request = [" + stopMandate.toString() + "]");
        System.out.println("");

        String url = credentials.getStopMandateLink();

        StopMandateResponse stopMandateResponse = stopMandate(url, stopMandate);

        System.out.println("");
        System.out.println("Stop Mandate response = [" + stopMandateResponse.toString() + "]");
        System.out.println("");

        if(stopMandateResponse.getStatuscode().equals("00")){

            Mandate mandate = mandateRepository.findByMandateIdContaining(stopMandateResponse.getMandateId());

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss").format(new Date());

            mandate.setStoppedDate(timeStamp);
            mandate.setStatus("STOPPED");

            mandateRepository.saveAndFlush(mandate);
        }

        return stopMandateResponse;
    }

    @Override
    public StopDebitResponse stopDebit(StopDebit stopDebit) throws Exception {

        System.out.println("");
        System.out.println("Stop debit request = [" + stopDebit.toString() + "]");
        System.out.println("");

        String url = credentials.getStopDebitLink();

        StopDebitResponse stopDebitResponse = stopDebit(url, stopDebit);

        System.out.println("");
        System.out.println("Stop debit response = [" + stopDebitResponse.toString() + "]");
        System.out.println("");

        return stopDebitResponse;

    }

    public MandateSetupResponse mandateSetup(MandateSetup mandateSetup) throws Exception {

        Mandate mandate = new Mandate();

        System.out.println("");
        System.out.println("mandate setup request = [" + mandateSetup.toString() + "]");
        System.out.println("");

        String url = credentials.getMandateSetupLink();

        MandateSetupResponse response = mandateSetup(url, mandateSetup);

        System.out.println("");
        System.out.println("mandate set up response = [" + response.toString() + "]");
        System.out.println("");

        if(response.getStatuscode().equals("040")){

            com.remita.directdebit.model.Bank bank = bankRepository.findByBankCodeContaining(mandateSetup.getPayerBankCode());
            String hash = getHash.getResponseHash(credentials.getMerchantId() + credentials.getApiKey() + response.getRequestId());
            String mandateLink = credentials.getViewMandateLink() + credentials.getMerchantId() + "/" + hash + "/" + response.getMandateId() + "/" + response.getRequestId() + "/rest.reg";

            String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss").format(new Date());
            BeanUtils.copyProperties(mandateSetup, mandate);
            mandate.setPayerBankCode(bank.getBankName());
            mandate.setMandateId(response.getMandateId());
            mandate.setCreatedDate(timeStamp);
            mandate.setMandateLink(mandateLink);
            mandate.setStatus("INACTIVE");

            mandateRepository.save(mandate);
        }
        return response;
    }
}
