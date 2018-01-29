package com.solutionsdelivery.directdebit.service;

import com.solutionsdelivery.directdebit.dao.*;
import com.solutionsdelivery.directdebit.dto.*;

import java.util.List;

public interface DirectDebitProcessRequestService {
	
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
