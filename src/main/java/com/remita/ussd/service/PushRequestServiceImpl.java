package com.remita.ussd.service;

import com.remita.ussd.dao.PushSMSRequest;
import com.remita.ussd.dao.PushSMSResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remita.ussd.dao.PushRequest;
import com.remita.ussd.dao.PushResponse;

@Service("pushRequestService")
public class PushRequestServiceImpl implements PushRequestService {

    protected ObjectMapper mapper = new ObjectMapper();

    public PushResponse getRequest(String url, Object... urlVariables) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity<>(createRequestHeader());
        ResponseEntity<PushResponse> response = getResetTemplate().exchange(url, HttpMethod.POST, requestObject, PushResponse.class,
                urlVariables);

        return response.getBody();
    }

    protected MultiValueMap<String, String> createRequestHeader() {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");

        return headers;
    }

    private RestTemplate getResetTemplate() {

        System.setProperty("http.proxyHost", "192.9.200.10");
        System.setProperty("http.proxyPort", "3128");
        System.setProperty("http.nonProxyHosts", StringUtils.EMPTY);

        return new RestTemplate();
    }

    public PushResponse pushRequest(PushRequest pushRequest) throws Exception {

        String url = "http://52.16.1.180:8080/push_ussd";

        return getRequest(url, pushRequest);
    }

}
