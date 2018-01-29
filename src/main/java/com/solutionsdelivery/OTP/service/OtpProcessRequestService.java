package com.solutionsdelivery.OTP.service;

import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.OTP.dao.ValidateOTP;
import com.solutionsdelivery.OTP.dto.RequestOtpResponse;
import com.solutionsdelivery.OTP.dto.ValidateOtpResponse;

public interface OtpProcessRequestService {

	RequestOtpResponse requestOtp(RequestOtp requestOtp) throws Exception;
	ValidateOtpResponse validateOtp(ValidateOTP validateOTP) throws Exception;

}
