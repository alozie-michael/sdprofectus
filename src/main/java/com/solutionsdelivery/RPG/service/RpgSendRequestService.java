package com.solutionsdelivery.RPG.service;

import com.solutionsdelivery.OTP.dao.RequestOtp;
import com.solutionsdelivery.RPG.dao.*;
import com.solutionsdelivery.RPG.dto.*;

public interface RpgSendRequestService {

    SinglePaymentResponse singlePayment(SinglePayment singlePayment, String initiator) throws Exception;
    BulkPaymentResponse bulkPayment(BulkPayment bulkPayment) throws Exception;
    PaymentStatusResponse paymentStatus(PaymentStatus paymentStatus) throws Exception;
    AccountEnquiryResponse accountEnquiry(RequestOtp accountEnquiry) throws Exception;
    GetBanksResponse getActiveBanks() throws Exception;
}
