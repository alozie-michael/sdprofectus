package com.remita.directdebit.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "debits")
public @Data class DebitInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MANDATEID")
    private String mandateId;

    @Column(name = "TOTALAMOUNT")
    private String totalAmount;

    @Column(name = "FUNDINGACCOUNT")
    private String fundingAccount;

    @Column(name = "FUNDINGBANK")
    private String fundingBank;

    @Column(name = "REQUESTID")
    private String requestId;

    @Column(name = "TRANSACTIONREF")
    private String transactionRef;

    @Column(name = "DATECREATED")
    private String dateCreated;

    @Column(name = "LASTDEBITAMOUNT")
    private String lastDebitAmount;

    @Column(name = "LASTDEBITDATE")
    private String lastDebitDate;

    @Column(name = "BALANCE")
    private String balance;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    private Mandate mandate;

}
