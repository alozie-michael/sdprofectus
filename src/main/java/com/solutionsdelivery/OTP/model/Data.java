package com.solutionsdelivery.OTP.model;

import javax.persistence.*;

@Entity
@Table(name = "DATA")
public @lombok.Data class Data {

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

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    private OtpRequestLogs otpRequestLogs;

}
