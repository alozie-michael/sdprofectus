package com.remita.directdebit.service;

import com.remita.directdebit.dao.*;
import com.remita.directdebit.dto.*;

import java.util.List;

public interface ProcessRequestService {
	
	MandateSetupResponse mandateSetup(MandateSetup mandateSetup) throws Exception;
	RequestOtpForMandateActivationResponse requestOtp(RequestOtpForMandateActivation requestOtpForMandateActivation) throws Exception;
	MandateActivationResponse validateOtp(MandateActivation mandateActivation) throws Exception;
	MandateStatusResponse mandateStatus(String mandateId) throws Exception;
	DebitInstructionResponse debitInstruction(DebitInstruction debitInstruction) throws Exception;
	StopDebitResponse stopDebit(StopDebit stopDebit) throws Exception;
	StopMandateResponse stopMandate(StopMandate stopMandate) throws Exception;
	String mandateActivationNotification(Notification notification);
	String debitNotification(Notification notification);
	List<Mandates> getMandates();
	List<MandateDebits> getDebits();
	List<Bank> getBanks();
}
