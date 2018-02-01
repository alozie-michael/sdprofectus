package com.solutionsdelivery.RPG.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rpg")
@NoArgsConstructor
public @Data class RpgCredentials {

    private String merchantId;
    private String apiKey;
    private String apiToken;
    private String iv;
    private String encKey;
    private String debitAccount;
    private String debitBankCode;
    private String requestOTPLink;
    private String validateOTPLink;
    private String singlePaymentLink;
    private String bulkPaymentLink;
    private String accountEnquiry;
    private String getActiveBanksLink;
    private String paymentStatusLink;

}
