package com.solutionsdelivery.directdebit.dto;

import lombok.Data;

public @Data class Mandates {

    private String mandateId;
    private String payerName;
    private String payerPhone;
    private String payerEmail;
    private String payerBankCode;
    private String payerAccount;
    private String requestId;
    private String startDate;
    private String endDate;
    private String amount;
    private String maxNoOfDebits;
    private String createdDate;
    private String activationDate;
    private String status;
    private String mandateLink;
}
