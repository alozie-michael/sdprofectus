package com.remita.directdebit.controller;

import com.remita.directdebit.dao.*;
import com.remita.directdebit.dto.*;
import com.remita.directdebit.service.ProcessRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class DirectDebitNotificationController {

    @Autowired
    ProcessRequestService processRequestService;

    @RequestMapping(value = "/remita/sd/Notification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mandateSetup(@RequestBody Notification notification) {

        String response;

        if (notification.getNotificationType().equals("ACTIVATION")){

            response = processRequestService.mandateActivationNotification(notification);

        }else {

            response = processRequestService.debitNotification(notification);

        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
