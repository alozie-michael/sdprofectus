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

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;

    private String request;
    @Column(name = "REQUESTTIMESTAMP")
    private String requestTimeStamp;

    @Column(length = 500)
    private String response;

    @Column(name = "RESPONSETIMESTAMP")
    private String responseTimeStamp;
}
