package com.solutionsdelivery.OTP.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTNUMBER")
public @Data
class AccountNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BANKCODE")
    private String bankCode;

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;
}
