package com.remita.directdebit.service;

import com.remita.directdebit.dao.*;
import com.remita.directdebit.dto.*;

public interface SendRequestService {

    MandateSetupResponse mandateSetup(MandateSetup mandateSetup) throws Exception;
    RequestOtpForMandateActivationResponse requestOtp(RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception;
    MandateActivationResponse validateOtp(MandateActivation mandateActivation) throws Exception;
    MandateStatusResponse mandateStatus(MandateStatus mandateStatus) throws Exception;
    DebitInstructionResponse debitInstruction(DebitInstruction debitInstruction) throws Exception;
    StopMandateResponse stopMandate(StopMandate stopMandate) throws Exception;
    StopDebitResponse stopDebit(StopDebit stopDebit) throws Exception;
}
