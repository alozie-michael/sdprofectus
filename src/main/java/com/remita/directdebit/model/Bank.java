package com.remita.directdebit.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "BANK")
public class Bank {

    @Id
    @Column(name = "BANKCODE")
    private String bankCode;

    @Column(name = "BANKNAME")
    private String bankName;

}
