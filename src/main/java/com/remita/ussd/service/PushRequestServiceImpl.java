package com.remita.ussd.service;

import com.remita.ussd.dao.PushSMSRequest;
import com.remita.ussd.dao.PushSMSResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remita.ussd.dao.PushRequest;
import com.remita.ussd.dao.PushResponse;

import java.util.Arrays;

@Service("pushRequestService")
public class PushRequestServiceImpl implements PushRequestService {

    protected ObjectMapper mapper = new ObjectMapper();

    public Object getRequest(String url, PushRequest pushRequest) throws Exception {

        HttpEntity<PushRequest> requestObject = new HttpEntity(pushRequest, createRequestHeader());
        ResponseEntity<Object> response = getResetTemplate().postForEntity(url, requestObject, Object.class);
        System.out.println("response = [" + response.getBody() + "]");
        return response.getBody();
    }

    protected HttpHeaders createRequestHeader() {

        HttpHeaders headers = new HttpHeaders();
        //headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        return headers;
    }

    private RestTemplate getResetTemplate() {

       /* System.setProperty("http.proxyHost", "192.9.200.10");
        System.setProperty("http.proxyPort", "3128");

        System.setProperty("http.nonProxyHosts", StringUtils.EMPTY);*/

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON}));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(messageConverter);
        return restTemplate;
    }

    public PushResponse pushRequest(PushRequest pushRequest) throws Exception {

        System.out.println("");
        System.out.println(pushRequest.getSessionId());
        System.out.println(pushRequest.getMSISDN());
        System.out.println(pushRequest.getUssdContent());
        System.out.println(pushRequest.getTimeStamp());
        System.out.println("");

        String url = "http://52.213.221.23:8080/push_ussd";

        PushResponse response = (PushResponse) getRequest(url, pushRequest);

        System.out.println("");
        System.out.println(response.getSessionId());
        System.out.println(response.getMSISDN());
        System.out.println(response.getErrorMsg());
        System.out.println(response.getErrorCode());
        System.out.println("");
        return response;
    }

}
