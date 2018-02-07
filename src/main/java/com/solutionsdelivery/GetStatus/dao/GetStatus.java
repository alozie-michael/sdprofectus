package com.solutionsdelivery.GetStatus.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetStatus {

    @JsonProperty("RRR")
    private String rrr;
    private String environment;
    private String merchDesc;
}
