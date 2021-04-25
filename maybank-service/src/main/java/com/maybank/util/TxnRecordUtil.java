package com.maybank.util;

import com.maybank.dataaccess.maybank.entity.TxnRecord;
import com.maybank.model.TxnRecordBO;

import java.util.List;
import java.util.stream.Collectors;

public class TxnRecordUtil {
    public static TxnRecord toTxnRecord(TxnRecordBO txnRecordBO){
        TxnRecord txnRecord = new TxnRecord();
        txnRecord.setTxnId(txnRecordBO.getTxnId());
        txnRecord.setAccountNumber(txnRecordBO.getAccountNumber());
        txnRecord.setCustomerId(txnRecordBO.getCustomerId());
        txnRecord.setDescription(txnRecordBO.getDescription());
        txnRecord.setTrxAmount(txnRecordBO.getTrxAmount());
        txnRecord.setTrxDate(txnRecordBO.getTrxDate());
        txnRecord.setTrxTime(txnRecordBO.getTrxTime());
        return txnRecord;
    }

    public static TxnRecordBO toTxnRecordBO(TxnRecord txnRecord){
        TxnRecordBO txnRecordBO = new TxnRecordBO();
        txnRecordBO.setTxnId(txnRecord.getTxnId());
        txnRecordBO.setAccountNumber(txnRecord.getAccountNumber());
        txnRecordBO.setCustomerId(txnRecord.getCustomerId());
        txnRecordBO.setDescription(txnRecord.getDescription());
        txnRecordBO.setTrxAmount(txnRecord.getTrxAmount());
        txnRecordBO.setTrxDate(txnRecord.getTrxDate());
        txnRecordBO.setTrxTime(txnRecord.getTrxTime());
        return txnRecordBO;
    }

    public static List<TxnRecord> toTxnRecords(List<TxnRecordBO> txnRecordBOs){
        return txnRecordBOs.stream().map(TxnRecordUtil::toTxnRecord).collect(Collectors.toList());
    }

    public static List<TxnRecordBO> toTxnRecordBOs(List<TxnRecord> txnRecords){
        return txnRecords.stream().map(TxnRecordUtil::toTxnRecordBO).collect(Collectors.toList());
    }
}
