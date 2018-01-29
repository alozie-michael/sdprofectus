package com.solutionsdelivery.directdebit.dao;

import lombok.Data;

import java.util.List;

public @Data class Notification {

    private String notificationType;
    private List<LineItems> lineItems;
}
