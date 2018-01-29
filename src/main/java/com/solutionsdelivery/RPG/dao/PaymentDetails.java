package com.solutionsdelivery.RPG.dao;

import lombok.Data;

@Data
public class PaymentDetails {

    private String amount;
    private String benficiaryAccountNumber;
    private String benficiaryBankCode;
    private String narration;
    private String benficiaryEmail;
    private String transRef;
}
