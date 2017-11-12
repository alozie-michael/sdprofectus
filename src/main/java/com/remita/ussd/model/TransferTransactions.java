package com.remita.ussd.model;

import javax.persistence.*;

@Entity
@Table(name = "transfertransactions")
public class TransferTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transferTransactions")
    private Activities activities;

    @Column(name = "MSISDN")
    private String msisdn;
    private String transactionReference;
    private String currencyCode;
    private String transactionAmount;
    private String transactionFee;
    private String transactionGross;
    private String debitStatusCode;
    private String debitStatusMessage;
    private String sourceAccountNumber;
    private String sourceAccountName;
    private String sourceBVN;
    private String sourceBankCode;
    private String sourceBankName;
    private String destinationAccountNumber;
    private String destinationAccountName;
    private String destinationBVN;
    private String destinationBankCode;
    private String destinationBankName;
    private String destinationTransactionReference;
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

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationBVN() {
        return destinationBVN;
    }

    public void setDestinationBVN(String destinationBVN) {
        this.destinationBVN = destinationBVN;
    }

    public String getDestinationBankCode() {
        return destinationBankCode;
    }

    public void setDestinationBankCode(String destinationBankCode) {
        this.destinationBankCode = destinationBankCode;
    }

    public String getDestinationBankName() {
        return destinationBankName;
    }

    public void setDestinationBankName(String destinationBankName) {
        this.destinationBankName = destinationBankName;
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

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }
}
