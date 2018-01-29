package com.solutionsdelivery.RPG.dao;

import lombok.Data;

@Data
public class BulkPaymentInfo {

    private String debitAccount;
    private String batchRef;
    private String narration;
    private String totalAmount;
    private String bankCode;

}
