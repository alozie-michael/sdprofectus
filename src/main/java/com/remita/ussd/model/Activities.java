package com.remita.ussd.model;

import javax.persistence.*;

@Entity
@Table(name = "activities")
public class Activities {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "sessionid")
    private String sessionID;

    @Column(name = "network")
    private String network;

    @Column(name = "payload")
    private String payload;

    @Column(name = "servicecode")
    private String serviceCode;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "createddate")
    private String createdDate;

    @Column(name = "modifiedby")
    private String modifiedBy;

    @Column(name = "modifieddate")
    private String modifiedDate;

    @Column(name = "deletedflag")
    private String deletedFlag;

    @Column(name = "deletedby")
    private String deletedBy;

    @Column(name = "deleteddate")
    private String deletedOn;

    @OneToOne
    private AirtimeTransactions airtimeTransactions;

    @OneToOne
    private BillerTransactions billerTransactions;

    @OneToOne
    private TransferTransactions transferTransactions;

    @OneToOne
    private Transactions transactions;

    public AirtimeTransactions getAirtimeTransactions() {
        return airtimeTransactions;
    }

    public void setAirtimeTransactions(AirtimeTransactions airtimeTransactions) {
        this.airtimeTransactions = airtimeTransactions;
    }

    public BillerTransactions getBillerTransactions() {
        return billerTransactions;
    }

    public void setBillerTransactions(BillerTransactions billerTransactions) {
        this.billerTransactions = billerTransactions;
    }

    public TransferTransactions getTransferTransactions() {
        return transferTransactions;
    }

    public void setTransferTransactions(TransferTransactions transferTransactions) {
        this.transferTransactions = transferTransactions;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
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
