package com.remita.directdebit.dto;

import lombok.Data;

public @Data class MandateStatusResponse {

    private String endDate;
    private String requestId;
    private String mandateId;
    private String activationDate;
    private String registrationDate;
    private String isActive;
    private String startDate;

}
