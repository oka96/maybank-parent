package com.maybank.service;

import com.maybank.model.TxnRecord;

import java.util.List;

public interface TxnRecordService {
    public List<TxnRecord> getTransaction(String customerId, String accountNumber, String description, int page, int pageSize);
    public int getTransactionCount(String customerId, String accountNumber, String description);
}
