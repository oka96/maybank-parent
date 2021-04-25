package com.maybank.model;

public class RawTxnRecord {
    private int txnId;
    private String accountNumber;
    private float trxAmount;
    private String description;
    private String trxDate;
    private String trxTime;
    private String customerId;

    public RawTxnRecord(){}

    public RawTxnRecord(String accountNumber, float trxAmount,
                       String description, String trxDate,
                       String time, String customerId) {
        this.accountNumber = accountNumber;
        this.trxAmount = trxAmount;
        this.description = description;
        this.trxDate = trxDate;
        this.trxTime = time;
        this.customerId = customerId;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getTrxAmount() {
        return trxAmount;
    }

    public void setTrxAmount(float trxAmount) {
        this.trxAmount = trxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxTime() {
        return trxTime;
    }

    public void setTrxTime(String trxTime) {
        this.trxTime = trxTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "RawTxnRecord{" +
                "txnId=" + txnId +
                ", accountNumber='" + accountNumber + '\'' +
                ", trxAmount='" + trxAmount + '\'' +
                ", description='" + description + '\'' +
                ", trxDate='" + trxDate + '\'' +
                ", trxTime='" + trxTime + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}