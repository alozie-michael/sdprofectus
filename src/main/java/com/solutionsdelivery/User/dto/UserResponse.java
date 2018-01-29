package com.solutionsdelivery.User.dto;

import java.util.List;

@lombok.Data
public class UserResponse {

    private String responseCode;
    private String responseMessage;
    private List<Data> data;

}
