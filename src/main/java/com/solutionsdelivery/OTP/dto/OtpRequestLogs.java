package com.solutionsdelivery.OTP.dto;

import lombok.Data;

public @Data
class OtpRequestLogs {

    private Long id;
    private String bank;
    private String accountNumber;
    private String request;
    private String requestTimeStamp;
    private String response;
    private String responseTimeStamp;
}
