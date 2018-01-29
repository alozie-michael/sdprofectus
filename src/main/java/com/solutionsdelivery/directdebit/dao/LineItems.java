package com.solutionsdelivery.directdebit.dao;

import lombok.Data;

public @Data class LineItems {

    private String mandateId;
    private String activationDate;
    private String requestId;
    private String startDate;
    private String endDate;
    private String amount;
    private String debitDate;

}
