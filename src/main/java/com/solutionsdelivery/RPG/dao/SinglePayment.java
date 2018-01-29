package com.solutionsdelivery.RPG.dao;

import lombok.Data;

@Data
public class SinglePayment {

    private String toBank;
    private String creditAccount;
    private String narration;
    private String amount;
    private String transRef;
    private String fromBank;
    private String debitAccount;
    private String beneficiaryEmail;

}
