package com.solutionsdelivery.directdebit.service;

import com.solutionsdelivery.directdebit.dao.*;
import com.solutionsdelivery.directdebit.dto.*;
import com.solutionsdelivery.directdebit.model.Mandate;
import com.solutionsdelivery.directdebit.model.Bank;
import com.solutionsdelivery.directdebit.repository.BankRepository;
import com.solutionsdelivery.directdebit.repository.DebitInstructionRepository;
import com.solutionsdelivery.directdebit.repository.MandateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DirectDebitProcessRequestServiceImpl implements DirectDebitProcessRequestService {

    private final
    DirectDebitSendRequestService directDebitSendRequestService;

    private final MandateRepository mandateRepository;

    private final
    DebitInstructionRepository debitInstructionRepository;

    private final BankRepository bankRepository;

    private Hash512Class getHash = new Hash512Class();

    //Static variables
    private String merchantId;
    private String serviceTypeId;
    private String apiKey;

    @Autowired
    public DirectDebitProcessRequestServiceImpl(DirectDebitSendRequestService directDebitSendRequestService, MandateRepository mandateRepository, DebitInstructionRepository debitInstructionRepository, BankRepository bankRepository, DirectDebitCredentials credentials) {
        this.directDebitSendRequestService = directDebitSendRequestService;
        this.mandateRepository = mandateRepository;
        this.debitInstructionRepository = debitInstructionRepository;
        this.bankRepository = bankRepository;
        merchantId = credentials.getMerchantId();
        serviceTypeId = credentials.getServiceTypeId();
        apiKey = credentials.getApiKey();
    }


    @Override
    public MandateSetupResponse mandateSetup(MandateSetup mandateSetup) throws Exception {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHss").format(new Date());

        mandateSetup.setMerchantId(merchantId);
        mandateSetup.setServiceTypeId(serviceTypeId);
        mandateSetup.setMandateType("DD");
        mandateSetup.setRequestId(timeStamp + "-SDMANDATESETUP");

        String stringToHash = merchantId + serviceTypeId + mandateSetup.getRequestId() + mandateSetup.getAmount() + apiKey;
        String hashedValue = getHash.getResponseHash(stringToHash);
        mandateSetup.setHash(hashedValue);

        return directDebitSendRequestService.mandateSetup(mandateSetup);
    }

    @Override
    public RequestOtpForMandateActivationResponse requestOtp(RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception {

        return directDebitSendRequestService.requestOtp(requestOtpForMandateActivation);
    }

    @Override
    public MandateActivationResponse validateOtp(MandateActivation mandateActivation) throws Exception {

        return directDebitSendRequestService.validateOtp(mandateActivation);
    }

    @Override
    public MandateStatusResponse mandateStatus(String mandateId) throws Exception {

        MandateStatus mandateStatus = new MandateStatus();
        Mandate mandate = mandateRepository.findByMandateIdContaining(mandateId);

        mandateStatus.setMerchantId(merchantId);
        mandateStatus.setRequestId(mandate.getRequestId());


        String hashedValue = getHash.getResponseHash(mandateStatus.getRequestId()+ apiKey + merchantId);

        mandateStatus.setHash(hashedValue);

        return directDebitSendRequestService.mandateStatus(mandateStatus);
    }

    @Override
    public DebitInstructionResponse debitInstruction(DebitInstruction debitInstruction) throws Exception {

        Mandate mandate = mandateRepository.findByMandateIdContaining(debitInstruction.getMandateId());
        Bank bank = bankRepository.findByBankCodeContaining(mandate.getPayerBankCode());

        if(mandate == null && bank == null)
            return new DebitInstructionResponse();

        String requestId = new SimpleDateFormat("yyyyMMddHHss").format(new Date());

        debitInstruction.setMerchantId(merchantId);
        debitInstruction.setServiceTypeId(serviceTypeId);
        debitInstruction.setRequestId(requestId + "-SDDEBITINSTRUCTION");
        debitInstruction.setFundingAccount(mandate.getPayerAccount());
        debitInstruction.setFundingBankCode(bank.getBankCode());

        String stringToHash = merchantId + serviceTypeId + debitInstruction.getRequestId() + debitInstruction.getTotalAmount() + apiKey;
        String hashedValue = getHash.getResponseHash(stringToHash);
        debitInstruction.setHash(hashedValue);

        return directDebitSendRequestService.debitInstruction(debitInstruction);
    }

    @Override
    public StopDebitResponse stopDebit(StopDebit stopDebit) throws Exception {

        com.solutionsdelivery.directdebit.model.DebitInstruction debitInstruction = debitInstructionRepository.findByRequestIdContaining(stopDebit.getRequestId());

        String stringToHash = stopDebit.getTransactionRef()+merchantId+stopDebit.getRequestId()+apiKey;
        String hashedValue = getHash.getResponseHash(stringToHash);

        stopDebit.setMandateId(debitInstruction.getMandateId());
        stopDebit.setHash(hashedValue);
        stopDebit.setMerchantId(merchantId);

        return directDebitSendRequestService.stopDebit(stopDebit);
    }

    @Override
    public StopMandateResponse stopMandate(StopMandate stopMandate) throws Exception {

        Mandate mandate = mandateRepository.findByMandateIdContaining(stopMandate.getMandateId());

        String stringToHash = stopMandate.getMandateId()+merchantId+mandate.getRequestId()+apiKey;
        String hashedValue = getHash.getResponseHash(stringToHash);

        stopMandate.setRequestId(mandate.getRequestId());
        stopMandate.setHash(hashedValue);
        stopMandate.setMerchantId(merchantId);

        return directDebitSendRequestService.stopMandate(stopMandate);
    }

    @Override
    public String mandateActivationNotification(Notification notification) {

        String response = "not OK";

        List<LineItems> lineItems = notification.getLineItems();
        for(LineItems lineItem : lineItems){

            String mandateId = lineItem.getMandateId();
            String activationDate = lineItem.getActivationDate();

            Mandate mandate = mandateRepository.findByMandateIdContaining(mandateId);

            if(mandate != null){

                mandate.setStatus("ACTIVE");
                mandate.setActivationDate(activationDate);

                mandateRepository.saveAndFlush(mandate);

                response = "OK";
            }
        }

        return response;
    }

    @Override
    public String debitNotification(Notification notification) {

        String response = "not OK";

        List<LineItems> lineItems = notification.getLineItems();

        for(LineItems lineItem : lineItems){

            String debitDate = lineItem.getMandateId();
            String amount = lineItem.getAmount();
            String mandateId = lineItem.getActivationDate();

            com.solutionsdelivery.directdebit.model.DebitInstruction debitInstruction = debitInstructionRepository.findByMandateIdContaining(mandateId);
            Mandate mandate = mandateRepository.findByMandateIdContaining(mandateId);

            if(debitInstruction != null && mandate != null){


                Integer mandateAmount = Integer.valueOf(mandate.getAmount());
                String balance = String.valueOf(mandateAmount - Integer.valueOf(amount));

                if(balance.equals("0"))
                    debitInstruction.setStatus("DONE");

                debitInstruction.setLastDebitDate(debitDate);
                debitInstruction.setBalance(balance);
                debitInstruction.setLastDebitAmount(amount);

                debitInstructionRepository.saveAndFlush(debitInstruction);

                response = "OK";
            }
        }

        return response;
    }

    @Override
    public List<Mandates> getMandates() {

        List<Mandates> newMandates = new ArrayList<>();
        List<Mandate> mandates = mandateRepository.findAll();

        if(mandates == null)
            return newMandates;

        for (Mandate newMandate : mandates) {

            Mandates mandate = new Mandates();
            BeanUtils.copyProperties(newMandate, mandate);
            newMandates.add(mandate);
        }

        return newMandates;
    }

    @Override
    public List<MandateDebits> getDebits() {


        List<MandateDebits> mandateDebits = new ArrayList<>();
        List<Mandate> mandates = mandateRepository.findAll();

        if(mandates == null)
            return mandateDebits;

        for (Mandate mandate : mandates) {

            List<com.solutionsdelivery.directdebit.model.DebitInstruction> debitInstructions = mandate.getDebitInstruction();
            List<DebitInstructions> newDebitInstruction = new ArrayList<>();

            MandateDebits mandateDebit = new MandateDebits();
            mandateDebit.setMandateId(mandate.getMandateId());

            for(com.solutionsdelivery.directdebit.model.DebitInstruction debitInstruction: debitInstructions){

                DebitInstructions debitInstructions1 = new DebitInstructions();

                BeanUtils.copyProperties(debitInstruction, debitInstructions1);
                newDebitInstruction.add(debitInstructions1);

            }

            mandateDebit.setDebitInstructions(newDebitInstruction);
            mandateDebits.add(mandateDebit);
        }

        return mandateDebits;
    }

    @Override
    public List<com.solutionsdelivery.directdebit.dto.Bank> getBanks() {

        List<com.solutionsdelivery.directdebit.dto.Bank> newBanks = new ArrayList<>();

        List<Bank> banks = bankRepository.findAll();

        for(com.solutionsdelivery.directdebit.model.Bank getBankFromBanks : banks){

            com.solutionsdelivery.directdebit.dto.Bank bank = new com.solutionsdelivery.directdebit.dto.Bank();

            BeanUtils.copyProperties(getBankFromBanks, bank);

            newBanks.add(bank);

        }

        return newBanks;
    }


}
