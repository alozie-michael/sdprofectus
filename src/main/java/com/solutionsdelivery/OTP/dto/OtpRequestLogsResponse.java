package com.solutionsdelivery.OTP.dto;

import lombok.Data;

import java.util.List;

public @Data
class OtpRequestLogsResponse {

    private String responseCode;
    private String responseMessage;
    private List<OtpRequestLogs> otpRequestLogs;
}
