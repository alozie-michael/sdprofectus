package com.solutionsdelivery.RPG.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BANKNAME")
    private String bankName;

    @Column(name = "BANKCODE")
    private String bankCode;

    @Column(name = "ACCOUNTNUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNTTOKEN")
    private String accountToken;

}
