package com.solutionsdelivery.directdebit.dto;

import lombok.Data;

public @Data class StopDebitResponse {

    private String statuscode;
    private String mandateId;
    private String status;
    private String requestId;
}
