package com.solutionsdelivery.directdebit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutionsdelivery.directdebit.dao.*;
import com.solutionsdelivery.directdebit.dto.*;
import com.solutionsdelivery.directdebit.model.Mandate;
import com.solutionsdelivery.directdebit.repository.BankRepository;
import com.solutionsdelivery.directdebit.repository.DebitInstructionRepository;
import com.solutionsdelivery.directdebit.repository.MandateRepository;
import lombok.extern.slf4j.Slf4j;
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

@Service
@Slf4j
public class DirectDebitSendRequestServiceImpl implements DirectDebitSendRequestService {

    @Autowired
    private final MandateRepository mandateRepository;
    private final DebitInstructionRepository debitInstructionRepository;
    private final BankRepository bankRepository;
    private final DirectDebitCredentials credentials;
    private ObjectMapper mapper = new ObjectMapper();
    private Hash512Class getHash = new Hash512Class();


    @Autowired
    public DirectDebitSendRequestServiceImpl(MandateRepository mandateRepository, DebitInstructionRepository debitInstructionRepository, BankRepository bankRepository, DirectDebitCredentials credentials) {
        this.mandateRepository = mandateRepository;
        this.debitInstructionRepository = debitInstructionRepository;
        this.bankRepository = bankRepository;
        this.credentials = credentials;
    }

    private RequestOtpForMandateActivationResponse requestOtp(String url, RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(requestOtpForMandateActivation, createRequestOtpHeader());
        log.info("Sending OTP request for mandate activation to Remita {}", requestObject.toString());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("OTP request response for mandate activation from Remita {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, RequestOtpForMandateActivationResponse.class);
    }

    private MandateActivationResponse validateOtp(String url, MandateActivation mandateActivation) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(mandateActivation, createRequestOtpHeader());
        log.info("Sending OTP validation request for mandate activation to Remita {}", requestObject.toString());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("OTP validation response for mandate activation from Remita {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, MandateActivationResponse.class);
    }

    private MandateSetupResponse mandateSetup(String url, MandateSetup mandateSetup) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(mandateSetup, createRequestHeader());
        log.info("Sending mandate setup request to Remita {}", requestObject.toString());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("Mandate setup response from Remita {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, MandateSetupResponse.class);
    }

    private StopMandateResponse stopMandate(String url, StopMandate stopMandate) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(stopMandate, createRequestHeader());
        log.info("Sending stop mandate request to Remita {}", requestObject.toString());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("stop mandate response from Remita {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, StopMandateResponse.class);
    }

    private StopDebitResponse stopDebit(String url, StopDebit stopDebit) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(stopDebit, createRequestHeader());
        log.info("Sending stop debit request to Remita {}", requestObject.toString());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("stop debit response from Remita {}", response.toString());

        //convert from jsonp to json
        String editedbody = response.getBody();
        editedbody = StringUtils.strip(editedbody, "jsonp( )");

        return mapper.readValue(editedbody, StopDebitResponse.class);
    }

    private MandateStatusResponse mandateStatus(String url) throws Exception {

        log.info("retrieving mandate status from Remita {}", url);
        ResponseEntity<String> response = getResetTemplate().getForEntity(url, String.class);
        log.info("mandate status response from Remita {}", response.toString());

        //convert from jsonp to json
        String editedBody = response.getBody();
        editedBody = StringUtils.strip(editedBody, "jsonp( )");

        return mapper.readValue(editedBody, MandateStatusResponse.class);
    }

    public DebitInstructionResponse debitInstruction(String url, DebitInstruction debitInstruction) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity(debitInstruction, createRequestHeader());
        log.info("sending debit instruction to Remita {}", requestObject.toString());
        ResponseEntity<String> response = getResetTemplate().postForEntity(url, requestObject, String.class);
        log.info("debit instruction response from Remita {}", response.toString());

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

        String requestTimeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        String stringToHash = credentials.getApiKey() + requestTimeStamp + credentials.getApiToken();
        String apiDetailsHash = getHash.getResponseHash(stringToHash);

        HttpHeaders headers = new HttpHeaders();
        headers.add("MERCHANT_ID", credentials.getMerchantId());
        headers.add("API_KEY", credentials.getApiKey());
        headers.add("REQUEST_ID", requestTimeStamp);
        headers.add("REQUEST_TS", requestTimeStamp + "+0000");
        headers.add("API_DETAILS_HASH", apiDetailsHash);

        return headers;
    }

    public RestTemplate getResetTemplate() {

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

        String url = credentials.getRequestOTPLink();
        return requestOtp(url, requestOtpForMandateActivation);
    }

    @Override
    public MandateActivationResponse validateOtp(MandateActivation mandateActivation) throws Exception {

        String url = credentials.getValidateOTPLink();
        MandateActivationResponse response = validateOtp(url, mandateActivation);

        if(response.getStatuscode().equals("00")){

            log.info("successful OTP validation. Updating Mandate - " + response.getMandateId() + "status to ACTIVE on DB");
            Mandate mandate = mandateRepository.findByMandateIdContaining(response.getMandateId());
            String activationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            mandate.setStatus("ACTIVE");
            mandate.setActivationDate(activationDate);

            mandateRepository.save(mandate);
            log.info("Mandate - " + response.getMandateId() + "status Updated.");
        }

        return response;
    }

    @Override
    public MandateStatusResponse mandateStatus(MandateStatus mandateStatus) throws Exception {

        String url = credentials.getMandateStatusLink() + mandateStatus.getMerchantId() + "/" + mandateStatus.getRequestId() + "/" + mandateStatus.getHash() + "/status.reg";
        return mandateStatus(url);
    }

    @Override
    public DebitInstructionResponse debitInstruction(DebitInstruction debitInstruction) throws Exception {

        String url = credentials.getDebitInstructionLink();
        DebitInstructionResponse response = debitInstruction(url, debitInstruction);

        if(response.getStatuscode().equals("069")){
            log.info("New #" + debitInstruction.getTotalAmount() + " debit passed on mandate - " + response.getMandateId() + " with Transaction ref - " + response.getTransactionRef());
            com.solutionsdelivery.directdebit.model.Bank bank = bankRepository.findByBankCodeContaining(debitInstruction.getFundingBankCode());
            com.solutionsdelivery.directdebit.model.DebitInstruction saveDebitInstruction = new com.solutionsdelivery.directdebit.model.DebitInstruction();

            BeanUtils.copyProperties(debitInstruction, saveDebitInstruction);

            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(new Date());
            saveDebitInstruction.setDateCreated(timeStamp);
            saveDebitInstruction.setFundingBank(bank.getBankName());
            saveDebitInstruction.setTransactionRef(response.getTransactionRef());
            saveDebitInstruction.setStatus("PROCESSING");

            log.info("Saving new debit with transaction ref - " + response.getTransactionRef() + " to Database.");
            Mandate mandate = mandateRepository.findByMandateIdContaining(response.getMandateId());
            List<com.solutionsdelivery.directdebit.model.DebitInstruction> debitInstructions = mandate.getDebitInstruction();

            log.info("checking if debit transactions exist for Mandate ID " + response.getMandateId());
            if(debitInstructions == null) {

                debitInstructions = new ArrayList<>();

                saveDebitInstruction.setMandate(mandate);
                debitInstructions.add(saveDebitInstruction);
                mandate.setDebitInstruction(debitInstructions);

                log.info("No existing debit found, new debit created for mandate Id - " + response.getMandateId() + " with transaction ID" + response.getTransactionRef() + " and persisted to database.");
                mandateRepository.saveAndFlush(mandate);

            }else {

                saveDebitInstruction.setMandate(mandate);
                debitInstructions.add(saveDebitInstruction);

                mandate.setDebitInstruction(debitInstructions);

                log.info("Existing debits found,saving debit for mandate Id - " + response.getMandateId() + " with transaction ID" + response.getTransactionRef() + " to database.");
                mandateRepository.save(mandate);
            }

        }

        return response;
    }

    @Override
    public StopMandateResponse stopMandate(StopMandate stopMandate) throws Exception {

        String url = credentials.getStopMandateLink();

        StopMandateResponse stopMandateResponse = stopMandate(url, stopMandate);

        if(stopMandateResponse.getStatuscode().equals("00")){
            log.info("Mandate ID - {} stopped successfully. Updating Status to 'STOPPED'", stopMandateResponse.getMandateId());
            Mandate mandate = mandateRepository.findByMandateIdContaining(stopMandateResponse.getMandateId());

            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(new Date());

            mandate.setStoppedDate(timeStamp);
            mandate.setStatus("STOPPED");


            mandateRepository.saveAndFlush(mandate);
            log.info("Status successfully updated to STOPPED for Mandate ID - {}", stopMandateResponse.getMandateId());
        }

        return stopMandateResponse;
    }

    @Override
    public StopDebitResponse stopDebit(StopDebit stopDebit) throws Exception {

        String url = credentials.getStopDebitLink();

        return stopDebit(url, stopDebit);

    }

    public MandateSetupResponse mandateSetup(MandateSetup mandateSetup) throws Exception {

        Mandate mandate = new Mandate();

        String url = credentials.getMandateSetupLink();
        MandateSetupResponse response = mandateSetup(url, mandateSetup);

        if(response.getStatuscode().equals("040")){
            log.info("New mandate created with Mandate ID - {}", response.getMandateId());
            com.solutionsdelivery.directdebit.model.Bank bank = bankRepository.findByBankCodeContaining(mandateSetup.getPayerBankCode());
            String hash = getHash.getResponseHash(credentials.getMerchantId() + credentials.getApiKey() + response.getRequestId());
            String mandateLink = credentials.getViewMandateLink() + credentials.getMerchantId() + "/" + hash + "/" + response.getMandateId() + "/" + response.getRequestId() + "/rest.reg";

            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss").format(new Date());
            BeanUtils.copyProperties(mandateSetup, mandate);
            mandate.setPayerBankCode(bank.getBankName());
            mandate.setMandateId(response.getMandateId());
            mandate.setCreatedDate(timeStamp);
            mandate.setMandateLink(mandateLink);
            mandate.setStatus("INACTIVE");

            log.info("Saving new Mandate ID - {} to database", response.getMandateId());
            mandateRepository.save(mandate);
            log.info("New Mandate ID - {} saved successfully to database", response.getMandateId());
        }
        return response;
    }
}
