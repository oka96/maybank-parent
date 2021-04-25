package com.maybank.service;

import com.maybank.model.TxnRecordBO;

import java.util.List;

public interface TxnRecordService {
    public List<TxnRecordBO> getTransaction(String customerId, String accountNumber, String description, int page, int pageSize);
    public int getTransactionCount(String customerId, String accountNumber, String description);
    public void insert(TxnRecordBO txnRecord);
}
