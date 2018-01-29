package com.solutionsdelivery.directdebit.service;

import com.solutionsdelivery.directdebit.dao.*;
import com.solutionsdelivery.directdebit.dto.*;

public interface DirectDebitSendRequestService {

    MandateSetupResponse mandateSetup(MandateSetup mandateSetup) throws Exception;
    RequestOtpForMandateActivationResponse requestOtp(RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception;
    MandateActivationResponse validateOtp(MandateActivation mandateActivation) throws Exception;
    MandateStatusResponse mandateStatus(MandateStatus mandateStatus) throws Exception;
    DebitInstructionResponse debitInstruction(DebitInstruction debitInstruction) throws Exception;
    StopMandateResponse stopMandate(StopMandate stopMandate) throws Exception;
    StopDebitResponse stopDebit(StopDebit stopDebit) throws Exception;
}
