package com.remita.directdebit.dto;

import lombok.Data;

public @Data class StopMandateResponse {

    private String statuscode;
    private String mandateId;
    private String status;
    private String requestId;
}
