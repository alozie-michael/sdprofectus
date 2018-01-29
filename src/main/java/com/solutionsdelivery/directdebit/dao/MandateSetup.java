package com.solutionsdelivery.directdebit.dao;

import lombok.Data;

public @Data class MandateSetup {

    private String merchantId;
    private String serviceTypeId;
    private String hash;
    private String payerName;
    private String payerPhone;
    private String payerEmail;
    private String payerBankCode;
    private String payerAccount;
    private String requestId;
    private String startDate;
    private String endDate;
    private String mandateType;
    private String amount;
    private String maxNoOfDebits;

}
