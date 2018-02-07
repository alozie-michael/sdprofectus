package com.solutionsdelivery.GetStatus.service;

import com.solutionsdelivery.GetStatus.dto.StatusResponse;

public interface MerchantStatusSendRequestService {

    StatusResponse getStatus(String url) throws Exception;
}
