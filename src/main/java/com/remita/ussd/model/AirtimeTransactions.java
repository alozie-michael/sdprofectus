package com.remita.ussd.model;

import javax.persistence.*;

@Entity
@Table(name = "airtimetransactions")
public class AirtimeTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private	Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "airtimeTransactions")
    private Activities activities;

    @Column(name = "MSISDN")
    private	String msisdn;
    private	String transactionReference;
    private	String currencyCode;
    private	String transactionAmount;
    private	String transactionFee;
    private	String transactionGross;
    private	String sourceAccountNumber;
    private	String sourceAccountName;
    private	String sourceBVN;
    private	String sourceBankCode;
    private	String sourceBankName;
    private	String destinationMSISDN;
    private	String destinationNetwork;
    private	String destinationTransactionReference;
    private	String destinationTransactionStatus;
    private	String destinationTransactionMessage;
    private	String createdby;
    private	String createdDate;
    private	String modifiedBy;
    private	String modifiedDate;
    private	String deletedFlag;
    private	String deletedBy;
    private	String deletedOn;

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(String transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getTransactionGross() {
        return transactionGross;
    }

    public void setTransactionGross(String transactionGross) {
        this.transactionGross = transactionGross;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceBVN() {
        return sourceBVN;
    }

    public void setSourceBVN(String sourceBVN) {
        this.sourceBVN = sourceBVN;
    }

    public String getSourceBankCode() {
        return sourceBankCode;
    }

    public void setSourceBankCode(String sourceBankCode) {
        this.sourceBankCode = sourceBankCode;
    }

    public String getSourceBankName() {
        return sourceBankName;
    }

    public void setSourceBankName(String sourceBankName) {
        this.sourceBankName = sourceBankName;
    }

    public String getDestinationMSISDN() {
        return destinationMSISDN;
    }

    public void setDestinationMSISDN(String destinationMSISDN) {
        this.destinationMSISDN = destinationMSISDN;
    }

    public String getDestinationNetwork() {
        return destinationNetwork;
    }

    public void setDestinationNetwork(String destinationNetwork) {
        this.destinationNetwork = destinationNetwork;
    }

    public String getDestinationTransactionReference() {
        return destinationTransactionReference;
    }

    public void setDestinationTransactionReference(String destinationTransactionReference) {
        this.destinationTransactionReference = destinationTransactionReference;
    }

    public String getDestinationTransactionStatus() {
        return destinationTransactionStatus;
    }

    public void setDestinationTransactionStatus(String destinationTransactionStatus) {
        this.destinationTransactionStatus = destinationTransactionStatus;
    }

    public String getDestinationTransactionMessage() {
        return destinationTransactionMessage;
    }

    public void setDestinationTransactionMessage(String destinationTransactionMessage) {
        this.destinationTransactionMessage = destinationTransactionMessage;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(String deletedOn) {
        this.deletedOn = deletedOn;
    }
}
