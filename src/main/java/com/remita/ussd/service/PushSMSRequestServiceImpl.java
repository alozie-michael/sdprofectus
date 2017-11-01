package com.remita.ussd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remita.ussd.dao.PushRequest;
import com.remita.ussd.dao.PushResponse;
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

@Service("pushSMSRequestService")
public class PushSMSRequestServiceImpl implements PushSMSRequestService {

    protected ObjectMapper mapper = new ObjectMapper();

    public PushSMSResponse getRequest(String url, Object... urlVariables) throws Exception {

        HttpEntity<String> requestObject = new HttpEntity<>(createRequestHeader());
        ResponseEntity<PushSMSResponse> response = getResetTemplate().exchange(url, HttpMethod.POST, requestObject, PushSMSResponse.class,
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

    @Override
    public PushSMSResponse pushSMSRequest(PushSMSRequest pushSMSRequest) throws Exception {

        String url = "";
        return getRequest(url,pushSMSRequest);
    }
}
