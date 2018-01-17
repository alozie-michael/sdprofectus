package com.remita.directdebit.dto;

import lombok.Data;

import java.util.List;

public @Data class MandateDebits {

    private String mandateId;
    private List<DebitInstructions> debitInstructions;
}
