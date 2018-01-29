package com.solutionsdelivery.directdebit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class DebitInstructionResponse {

    private String mandateId;
    @JsonProperty("RRR")
    private String rrr;
    private String requestId;
    private String transactionRef;
    private String statuscode;
    private String status;

}
