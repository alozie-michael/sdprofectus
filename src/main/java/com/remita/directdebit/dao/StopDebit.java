package com.remita.directdebit.dao;

import lombok.Data;

public @Data class StopDebit {

    private String merchantId;
    private String mandateId;
    private String hash;
    private String requestId;
    private String transactionRef;

}
