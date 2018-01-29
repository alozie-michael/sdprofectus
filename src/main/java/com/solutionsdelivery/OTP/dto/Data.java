package com.solutionsdelivery.OTP.dto;

import java.util.List;

@lombok.Data
public class Data {

    private String remitaTransRef;
    private String responseCode;
    private String responseId;
    private List<AuthParams> authParams;
    private String responseDescription;
    private String mandateNumber;
    private String accountToken;
}
