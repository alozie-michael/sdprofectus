package com.solutionsdelivery.RPG.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String toBank;
    private String creditAccount;
    private String narration;
    private String amount;
    private String transRef;
    private String fromBank;
    private String debitAccount;
    private String beneficiaryEmail;
    private String initiator;
    private String initiatedDate;

}
