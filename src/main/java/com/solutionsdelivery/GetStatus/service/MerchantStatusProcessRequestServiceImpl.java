package com.solutionsdelivery.GetStatus.service;


import com.solutionsdelivery.GetStatus.dao.AddMerchant;
import com.solutionsdelivery.GetStatus.dao.GetStatus;
import com.solutionsdelivery.GetStatus.dto.Data;
import com.solutionsdelivery.GetStatus.dto.MerchantResponse;
import com.solutionsdelivery.GetStatus.dto.StatusResponse;
import com.solutionsdelivery.GetStatus.model.Merchants;
import com.solutionsdelivery.GetStatus.repository.MerchantsRepository;
import com.solutionsdelivery.directdebit.dao.Hash512Class;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("merchantStatusProcessRequestService")
@Slf4j
public class MerchantStatusProcessRequestServiceImpl implements MerchantStatusProcessRequestService {

    private Hash512Class hash512Class = new Hash512Class();
    private final MerchantsRepository merchantsRepository;
    private final MerchantStatusSendRequestService merchantStatusSendRequestService;

    @Autowired
    public MerchantStatusProcessRequestServiceImpl(MerchantsRepository merchantsRepository, MerchantStatusSendRequestService merchantStatusSendRequestService){
        this.merchantsRepository = merchantsRepository;
        this.merchantStatusSendRequestService = merchantStatusSendRequestService;
    }

    @Override
    public MerchantResponse addMerchant(AddMerchant addMerchant) {

        MerchantResponse merchantResponse = new MerchantResponse();
        log.info("checking if {} exist in DB", addMerchant.getMerchantName());
        Merchants merchants = merchantsRepository.findByMerchantIdContaining(addMerchant.getMerchantId());

        if(merchants != null){

            log.error("merchant already exist");
            merchantResponse.setResponseCode("01");
            merchantResponse.setResponseMessage("merchant already exist");

        }else {

            Merchants merchant = new Merchants();
            BeanUtils.copyProperties(addMerchant, merchant);
            log.info("saving to DB, new merchant {}", addMerchant.getMerchantName());
            merchantsRepository.save(merchant);

            merchantResponse.setResponseCode("00");
            merchantResponse.setResponseMessage("successful");

        }

        return merchantResponse;
    }

    @Override
    public MerchantResponse getMerchants() {

        MerchantResponse merchantResponse = new MerchantResponse();
        List<Merchants> merchants = merchantsRepository.findAll();

        if(merchants == null){
            merchantResponse.setResponseCode("01");
            merchantResponse.setResponseMessage("no merchant found");
        }

        List<Data> data = new ArrayList<>();

        assert merchants != null;
        for(Merchants merchant : merchants){
            Data data1 = new Data();
            BeanUtils.copyProperties(merchant, data1);

            data.add(data1);
        }

        merchantResponse.setResponseCode("00");
        merchantResponse.setResponseMessage("successful");
        merchantResponse.setData(data);

        return merchantResponse;
    }

    @Override
    public StatusResponse getStatus(GetStatus getStatus) throws Exception {

        StatusResponse statusResponse;

        if(getStatus.getEnvironment().equals("demo")){

            String merchantId = "2547916";
            String apiKey = "1946";
            String hash = hash512Class.getResponseHash(getStatus.getRrr() + apiKey + merchantId);
            String url = "http://www.remitademo.net/remita/ecomm/" + merchantId + "/" + getStatus.getRrr() + "/" + hash + "/status.reg";
            statusResponse = merchantStatusSendRequestService.getStatus(url);

        }else {

            log.info("fetching merchant with description {} from DB", getStatus.getMerchDesc());
            Merchants merchants = merchantsRepository.findByDescriptionContaining(getStatus.getMerchDesc());

            if(merchants == null){
                return new StatusResponse();
            }

            String hash = hash512Class.getResponseHash(getStatus.getRrr() + merchants.getApiKey() + merchants.getMerchantId());
            String url = "https://login.remita.net/remita/ecomm/" + merchants.getMerchantId() + "/" + getStatus.getRrr() + "/" + hash + "/status.reg";
            statusResponse = merchantStatusSendRequestService.getStatus(url);

        }

        return statusResponse;
    }
}
