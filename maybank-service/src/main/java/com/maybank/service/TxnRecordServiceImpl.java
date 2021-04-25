package com.maybank.service;

import com.maybank.dataaccess.maybank.dao.gen.TxnRecordExtMapper;
import com.maybank.dataaccess.maybank.entity.TxnRecord;
import com.maybank.model.TxnRecordBO;
import com.maybank.util.TxnRecordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxnRecordServiceImpl implements TxnRecordService {
    @Autowired
    private TxnRecordExtMapper txnRecordExtMapper;

    @Override
    public List<TxnRecordBO> getTransaction(String customerId, String accountNumber, String description, int page, int pageSize) {
        int startIndex = page*pageSize;
        List<TxnRecord> txnRecords = txnRecordExtMapper.getTransaction(customerId, accountNumber, description, startIndex, pageSize);
        return TxnRecordUtil.toTxnRecordBOs(txnRecords);
    }

    @Override
    public int getTransactionCount(String customerId, String accountNumber, String description) {
        return txnRecordExtMapper.getTransactionCount(customerId, accountNumber, description);
    }

    @Override
    public void insert(TxnRecordBO txnRecordBO) {
        TxnRecord txnRecord = TxnRecordUtil.toTxnRecord(txnRecordBO);
        txnRecordExtMapper.insert(txnRecord);
    }
}