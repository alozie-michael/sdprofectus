package com.solutionsdelivery.RPG.service;


import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;
import com.solutionsdelivery.RPG.dao.*;
import com.solutionsdelivery.RPG.dto.*;

public interface RpgProcessRequestService {

	RequestOtpResponse requestOtp(RequestOtp requestOtp) throws Exception;
	ValidateOtpResponse validateOtp(ValidateOTP validateOTP) throws Exception;
	SinglePaymentResponse singlePayment(SinglePayment singlePayment, String initiator) throws Exception;
	BulkPaymentResponse bulkPayment(BulkPayment bulkPayment) throws Exception;
	PaymentStatusResponse paymentStatus(PaymentStatus paymentStatus) throws Exception;
	AccountEnquiryResponse accountEnquiry(RequestOtp accountEnquiry) throws Exception;
	GetBanksResponse getBanks() throws Exception;
}
