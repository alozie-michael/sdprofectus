package com.remita.directdebit.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sd")
@NoArgsConstructor
public @Data
class Credentials {

    private String merchantId;
    private String serviceTypeId;
    private String apiKey;
    private String apiToken;
    private String requestOTPLink;
    private String validateOTPLink;
    private String mandateStatusLink;
    private String debitInstructionLink;
    private String stopMandateLink;
    private String stopDebitLink;
    private String mandateSetupLink;
    private String viewMandateLink;

}
