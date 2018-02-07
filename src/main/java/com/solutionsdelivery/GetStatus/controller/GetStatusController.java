package com.solutionsdelivery.GetStatus.controller;

import com.solutionsdelivery.GetStatus.dao.AddMerchant;
import com.solutionsdelivery.GetStatus.dao.GetStatus;
import com.solutionsdelivery.GetStatus.dto.MerchantResponse;
import com.solutionsdelivery.GetStatus.dto.StatusResponse;
import com.solutionsdelivery.GetStatus.service.MerchantStatusProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/remita/status/")
public class GetStatusController {

    private final MerchantStatusProcessRequestService merchantStatusProcessRequestService;

    @Autowired
    public GetStatusController(MerchantStatusProcessRequestService merchantStatusProcessRequestService) {
        this.merchantStatusProcessRequestService = merchantStatusProcessRequestService;
    }

    @RequestMapping(value = "addMerchant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MerchantResponse> addMerchant(@RequestBody AddMerchant addMerchant) {

        MerchantResponse merchantResponse = new MerchantResponse();

        try {

            merchantResponse = merchantStatusProcessRequestService.addMerchant(addMerchant);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(merchantResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "getMerchants", method = RequestMethod.GET)
    public ResponseEntity<MerchantResponse> getMerchants() {

        MerchantResponse merchantResponse = new MerchantResponse();

        try {

            merchantResponse = merchantStatusProcessRequestService.getMerchants();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(merchantResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "getStatus", method = RequestMethod.POST)
    public ResponseEntity<StatusResponse> getStatus(@RequestBody GetStatus getStatus) {

        StatusResponse statusResponse = new StatusResponse();

        try {

            statusResponse = merchantStatusProcessRequestService.getStatus(getStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(statusResponse, HttpStatus.OK);
    }

}
