package com.solutionsdelivery.directdebit.controller;

import com.solutionsdelivery.directdebit.dao.*;
import com.solutionsdelivery.directdebit.dto.*;
import com.solutionsdelivery.directdebit.service.DirectDebitProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/remita/directdebit/")
public class DirectDebitController {

    @Autowired
    DirectDebitProcessRequestService directDebitProcessRequestService;

    @RequestMapping(value = "mandateSetup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MandateSetupResponse> mandateSetup(@RequestBody MandateSetup mandateSetup) {

        MandateSetupResponse mandateSetupResponse = new MandateSetupResponse();

        try {

            mandateSetupResponse = directDebitProcessRequestService.mandateSetup(mandateSetup);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(mandateSetupResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "requestOtp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestOtpForMandateActivationResponse> requestOtp(@RequestBody RequestOtpForMandateActivation requestOtpForMandateActivation) {

        RequestOtpForMandateActivationResponse requestOtpForMandateActivationResponse = new RequestOtpForMandateActivationResponse();

        try {

            requestOtpForMandateActivationResponse = directDebitProcessRequestService.requestOtp(requestOtpForMandateActivation);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(requestOtpForMandateActivationResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "validateOTP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MandateActivationResponse> validateOtp(@RequestBody MandateActivation mandateActivation) {

        MandateActivationResponse mandateActivationResponse = new MandateActivationResponse();

        try {

            mandateActivationResponse = directDebitProcessRequestService.validateOtp(mandateActivation);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(mandateActivationResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "mandateStatus/{mandateId}", method = RequestMethod.GET)
    public ResponseEntity<MandateStatusResponse> mandateStatus(@PathVariable("mandateId") String mandateId) {

        MandateStatusResponse mandateStatusResponse = new MandateStatusResponse();

        try {

            mandateStatusResponse = directDebitProcessRequestService.mandateStatus(mandateId);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(mandateStatusResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "debitInstruction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DebitInstructionResponse> debitInstruction(@RequestBody DebitInstruction debitInstruction) {

        DebitInstructionResponse debitInstructionResponse = new DebitInstructionResponse();

        try {

            debitInstructionResponse = directDebitProcessRequestService.debitInstruction(debitInstruction);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(debitInstructionResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "stopDebit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StopDebitResponse> stopDebit(@RequestBody StopDebit stopDebit) {

        StopDebitResponse stopDebitResponse = new StopDebitResponse();

        try {

            stopDebitResponse = directDebitProcessRequestService.stopDebit(stopDebit);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(stopDebitResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "stopMandate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StopMandateResponse> stopMandate(@RequestBody StopMandate stopMandate) {

        StopMandateResponse stopMandateResponse = new StopMandateResponse();

        try {

            stopMandateResponse = directDebitProcessRequestService.stopMandate(stopMandate);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(stopMandateResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "getMandates", method = RequestMethod.GET)
    public ResponseEntity<List<Mandates>> getMandates() {

        List<Mandates> mandates = directDebitProcessRequestService.getMandates();

        return new ResponseEntity<>(mandates, HttpStatus.OK);
    }

    @RequestMapping(value = "getDebits", method = RequestMethod.GET)
    public ResponseEntity<List<MandateDebits>> getDebits() {

        List<MandateDebits> debitInstructions = directDebitProcessRequestService.getDebits();

        return new ResponseEntity<>(debitInstructions, HttpStatus.OK);
    }

    @RequestMapping(value = "getBanks", method = RequestMethod.GET)
    public ResponseEntity<List<Bank>> getBanks() {

        List<Bank> banks = directDebitProcessRequestService.getBanks();

        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

}
