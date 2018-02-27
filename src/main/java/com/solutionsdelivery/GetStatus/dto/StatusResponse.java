package com.solutionsdelivery.GetStatus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusResponse {

	private Double amount;
	@JsonProperty("RRR")
	private String rrr;
	private String orderId;
	private String message;
	@JsonProperty("transactiontime")
	private String transactionTime;
	private String status;
	private String paymentDate;
}
