package com.solutionsdelivery.OTP.dto;

import lombok.Data;

import java.util.List;

public @Data
class OtpRequestLogs {

    private String startTime;
    private List<RequestResponse> requestResponses;

}
