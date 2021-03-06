package com.solutionsdelivery.GetStatus.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutionsdelivery.GetStatus.dto.StatusResponse;
import com.solutionsdelivery.directdebit.service.DirectDebitSendRequestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MerchantStatusSendRequestServiceImpl implements MerchantStatusSendRequestService {

    private final DirectDebitSendRequestServiceImpl directDebitSendRequestService;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MerchantStatusSendRequestServiceImpl(DirectDebitSendRequestServiceImpl directDebitSendRequestService) {
        this.directDebitSendRequestService = directDebitSendRequestService;
    }

    public StatusResponse getStatus(String url) throws Exception {

        log.info("retrieving payment status from Remita - ", url);
        ResponseEntity<String> response = directDebitSendRequestService.getResetTemplate().getForEntity(url, String.class);
        log.info("payment status response from Remita - ", response.toString());

        //convert from jsonp to json
        String editedBody = response.getBody();
        editedBody = StringUtils.strip(editedBody, "jsonp( )");

        return mapper.readValue(editedBody, StatusResponse.class);
    }

}
