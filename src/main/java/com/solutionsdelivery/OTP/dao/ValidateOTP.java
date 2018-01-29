package com.solutionsdelivery.OTP.dao;

import lombok.Data;

import java.util.List;

public @Data class ValidateOTP {

    private String remitaTransRef;
    private List<AuthParams> authParams;
}
