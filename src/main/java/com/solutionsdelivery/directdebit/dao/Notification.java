package com.solutionsdelivery.directdebit.dao;

import lombok.Data;

import java.util.List;

@Data
public class Notification {

    private String notificationType;
    private List<LineItems> lineItems;

}
