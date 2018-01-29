package com.solutionsdelivery.directdebit.dto;

import lombok.Data;

public @Data class MandateSetupResponse {

    private String statuscode;
    private String requestId;
    private String mandateId;
    private String status;

}
