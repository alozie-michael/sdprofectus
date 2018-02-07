package com.solutionsdelivery.GetStatus.dto;

import java.util.List;

@lombok.Data
public class MerchantResponse {

    private String responseCode;
    private String responseMessage;
    private List<Data> data;
}
