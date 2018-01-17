package com.remita.directdebit.dao;

import lombok.Data;

public @Data class MandateStatus {

    private String merchantId;
    private String requestId;
    private String hash;
}
