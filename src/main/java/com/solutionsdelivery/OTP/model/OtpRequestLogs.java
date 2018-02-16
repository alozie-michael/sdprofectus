package com.solutionsdelivery.OTP.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "OTPREQUESTLOGS")
public @Data
class OtpRequestLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bank;
    private String accountNumber;
    private String request;
    private String requestTimeStamp;
    @Column(length = 500)
    private String response;
    private String responseTimeStamp;
}
