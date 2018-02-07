package com.solutionsdelivery.GetStatus.service;

import com.solutionsdelivery.GetStatus.dao.AddMerchant;
import com.solutionsdelivery.GetStatus.dao.GetStatus;
import com.solutionsdelivery.GetStatus.dto.MerchantResponse;
import com.solutionsdelivery.GetStatus.dto.StatusResponse;

public interface MerchantStatusProcessRequestService {

	MerchantResponse addMerchant(AddMerchant addMerchant);
	MerchantResponse getMerchants();
	StatusResponse getStatus(GetStatus getStatus) throws Exception;

}
