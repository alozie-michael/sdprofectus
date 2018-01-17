package com.remita.directdebit.controller;

import com.remita.directdebit.dao.*;
import com.remita.directdebit.dto.*;
import com.remita.directdebit.service.ProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DirectDebitController {

    @Autowired
    ProcessRequestService processRequestService;

    @RequestMapping(value = "/remita/sd/mandateSetup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MandateSetupResponse> mandateSetup(@RequestBody MandateSetup mandateSetup) {

        MandateSetupResponse mandateSetupResponse = new MandateSetupResponse();

        try {

            mandateSetupResponse = processRequestService.mandateSetup(mandateSetup);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(mandateSetupResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/requestOtp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestOtpForMandateActivationResponse> requestOtp(@RequestBody RequestOtpForMandateActivation requestOtpForMandateActivation) {

        RequestOtpForMandateActivationResponse requestOtpForMandateActivationResponse = new RequestOtpForMandateActivationResponse();

        try {

            requestOtpForMandateActivationResponse = processRequestService.requestOtp(requestOtpForMandateActivation);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(requestOtpForMandateActivationResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/validateOTP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MandateActivationResponse> validateOtp(@RequestBody MandateActivation mandateActivation) {

        MandateActivationResponse mandateActivationResponse = new MandateActivationResponse();

        try {

            mandateActivationResponse = processRequestService.validateOtp(mandateActivation);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(mandateActivationResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/mandateStatus/{mandateId}", method = RequestMethod.GET)
    public ResponseEntity<MandateStatusResponse> mandateStatus(@PathVariable("mandateId") String mandateId) {

        MandateStatusResponse mandateStatusResponse = new MandateStatusResponse();

        try {

            mandateStatusResponse = processRequestService.mandateStatus(mandateId);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(mandateStatusResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/debitInstruction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DebitInstructionResponse> debitInstruction(@RequestBody DebitInstruction debitInstruction) {

        DebitInstructionResponse debitInstructionResponse = new DebitInstructionResponse();

        try {

            debitInstructionResponse = processRequestService.debitInstruction(debitInstruction);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(debitInstructionResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/stopDebit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StopDebitResponse> stopDebit(@RequestBody StopDebit stopDebit) {

        StopDebitResponse stopDebitResponse = new StopDebitResponse();

        try {

            stopDebitResponse = processRequestService.stopDebit(stopDebit);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(stopDebitResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/stopMandate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StopMandateResponse> stopMandate(@RequestBody StopMandate stopMandate) {

        StopMandateResponse stopMandateResponse = new StopMandateResponse();

        try {

            stopMandateResponse = processRequestService.stopMandate(stopMandate);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ResponseEntity<>(stopMandateResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/getMandates", method = RequestMethod.GET)
    public ResponseEntity<List<Mandates>> getMandates() {

        List<Mandates> mandates = processRequestService.getMandates();

        return new ResponseEntity<>(mandates, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/getDebits", method = RequestMethod.GET)
    public ResponseEntity<List<MandateDebits>> getDebits() {

        List<MandateDebits> debitInstructions = processRequestService.getDebits();

        return new ResponseEntity<>(debitInstructions, HttpStatus.OK);
    }

    @RequestMapping(value = "/remita/sd/getBanks", method = RequestMethod.GET)
    public ResponseEntity<List<Bank>> getBanks() {

        List<Bank> banks = processRequestService.getBanks();

        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

}
