package com.solutionsdelivery.directdebit.dto;

import lombok.Data;

public @Data class DebitInstructions {

    private String totalAmount;
    private String fundingAccount;
    private String fundingBank;
    private String requestId;
    private String transactionRef;
    private String dateCreated;
    private String lastDebitAmount;
    private String lastDebitDate;
    private String balance;
}
