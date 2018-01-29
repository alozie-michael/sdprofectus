package com.solutionsdelivery.RPG.dao;

import lombok.Data;

import java.util.List;

@Data
public class BulkPayment {

    private BulkPaymentInfo bulkPaymentInfo;
    private List<PaymentDetails> paymentDetails;
}
