package com.solutionsdelivery.RPG.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private String remitaTransRef;
    private String responseCode;
    private String responseId;
    private String responseDescription;
    private String mandateNumber;
    private String accountToken;
    private String transDate;
    private String rrr;
    private String amount;
    private String debitAccount;
    private String paymentStatusCode;
    private String paymentDate;
    private String settlementDate;
    private String creditAccount;
    private String toBank;
    private String authorizationId;
    private String paymentStatus;
    private String transRef;
    private String accountName;
    private String phoneNumber;
    private String email;
    private String accountNo;
    private String bankCode;
    private List<Banks> banks;

}
