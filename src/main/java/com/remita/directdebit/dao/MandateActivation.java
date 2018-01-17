package com.remita.directdebit.dao;

import lombok.Data;

import java.util.List;

public @Data class MandateActivation {

    private String remitaTransRef;
    private List<AuthParams> authParams;
}
