package com.remita.directdebit.dao;

import lombok.Data;

public @Data class RequestOtpForMandateActivation {

    private String mandateId;
    private String requestId;

}
