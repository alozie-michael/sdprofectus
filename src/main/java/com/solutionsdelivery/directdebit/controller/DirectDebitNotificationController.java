package com.solutionsdelivery.directdebit.controller;

import com.solutionsdelivery.directdebit.dao.*;
import com.solutionsdelivery.directdebit.service.DirectDebitProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class DirectDebitNotificationController {

    @Autowired
    DirectDebitProcessRequestService directDebitProcessRequestService;

    @RequestMapping(value = "/remita/sd/Notification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mandateSetup(@RequestBody Notification notification) {

        String response;

        if (notification.getNotificationType().equals("ACTIVATION")){

            response = directDebitProcessRequestService.mandateActivationNotification(notification);

        }else {

            response = directDebitProcessRequestService.debitNotification(notification);

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
