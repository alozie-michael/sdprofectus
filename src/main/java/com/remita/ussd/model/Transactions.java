package com.remita.ussd.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transactions")
    private Activities activities;

    @Column(name = "MSISDN")
    private String msisdn;
    private String transactionType;
    private String transacttionReference;
    private String transactionStartTime;
    private String transactionEndTime;
    private String currencyCode;
    private String amount;
    private String debitStatusCode;
    private String debitStatusMessage;
    private String destinationTransactionStatus;
    private String destinationTransactionMessage;
    private String createdby;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String deletedFlag;
    private String deletedBy;
    private String deletedDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransacttionReference() {
        return transacttionReference;
    }

    public void setTransacttionReference(String transacttionReference) {
        this.transacttionReference = transacttionReference;
    }

    public String getTransactionStartTime() {
        return transactionStartTime;
    }

    public void setTransactionStartTime(String transactionStartTime) {
        this.transactionStartTime = transactionStartTime;
    }

    public String getTransactionEndTime() {
        return transactionEndTime;
    }

    public void setTransactionEndTime(String transactionEndTime) {
        this.transactionEndTime = transactionEndTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDebitStatusCode() {
        return debitStatusCode;
    }

    public void setDebitStatusCode(String debitStatusCode) {
        this.debitStatusCode = debitStatusCode;
    }

    public String getDebitStatusMessage() {
        return debitStatusMessage;
    }

    public void setDebitStatusMessage(String debitStatusMessage) {
        this.debitStatusMessage = debitStatusMessage;
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

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }
}
