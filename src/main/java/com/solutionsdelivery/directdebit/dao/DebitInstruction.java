package com.solutionsdelivery.directdebit.dao;

import lombok.Data;

public @Data class DebitInstruction {

    private String merchantId;
    private String serviceTypeId;
    private String requestId;
    private String hash;
    private String totalAmount;
    private String mandateId;
    private String fundingAccount;
    private String fundingBankCode;

}
