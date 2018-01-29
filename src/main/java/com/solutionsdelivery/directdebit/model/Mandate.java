package com.solutionsdelivery.directdebit.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MANDATE")
public @Data class Mandate {

    @Id
    @Column(name = "MANDATEID")
    private String mandateId;

    @Column(name = "PAYERNAME")
    private String payerName;

    @Column(name = "PAYERPHONE")
    private String payerPhone;

    @Column(name = "PAYEREMAIL")
    private String payerEmail;

    @Column(name = "PAYERBANKCODE")
    private String payerBankCode;

    @Column(name = "PAYERACCOUNT")
    private String payerAccount;

    @Column(name = "REQUESTID")
    private String requestId;

    @Column(name = "STARTDATE")
    private String startDate;

    @Column(name = "ENDDATE")
    private String endDate;

    @Column(name = "MANDATETYPE")
    private String mandateType;

    @Column(name = "AMOUNT")
    private String amount;

    @Column(name = "MAXNOOFDEBITS")
    private String maxNoOfDebits;

    @Column(name = "CREATEDDATE")
    private String createdDate;

    @Column(name = "ACTIVATIONDATE")
    private String activationDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STOPPEDDATE")
    private String stoppedDate;

    @Column(name = "MANDATELINK")
    private String mandateLink;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="mandate")
    @Column(name = "DEBITINSTRUCTION")
    private List<DebitInstruction> debitInstruction;

}
