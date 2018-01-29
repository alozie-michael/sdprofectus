package com.solutionsdelivery.directdebit.dao;

import lombok.Data;

public @Data class StopMandate {

    private String merchantId;
    private String mandateId;
    private String hash;
    private String requestId;

}
