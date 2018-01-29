package com.solutionsdelivery.OTP.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "otp")
@NoArgsConstructor
public @Data class OtpCredentials {

    private String merchantId;
    private String apiKey;
    private String apiToken;
    private String requestOTPLink;
    private String validateOTPLink;
}
