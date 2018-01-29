package com.solutionsdelivery.directdebit.dto;

import lombok.Data;
import java.util.List;

public @Data class RequestOtpForMandateActivationResponse {

    private String statuscode;
    private List<AuthParams> authParams;
    private String requestId;
    private String mandateId;
    private String remitaTransRef;
    private String status;

}
