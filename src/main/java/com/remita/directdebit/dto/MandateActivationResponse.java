package com.remita.directdebit.dto;

import lombok.Data;

public @Data class MandateActivationResponse {

    private String statuscode;
    private String mandateId;
    private String status;
}
