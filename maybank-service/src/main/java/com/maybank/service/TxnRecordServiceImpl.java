package com.maybank.service;

import com.maybank.dataaccess.maybank.dao.gen.TxnRecordMapper;
import com.maybank.dataaccess.maybank.entity.TxnRecord;
import com.maybank.model.TxnRecordBO;
import com.maybank.util.StringUtil;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TxnRecordServiceImpl implements TxnRecordService {

    List<TxnRecordBO> txnRecords = new ArrayList(){
        {
            add(new TxnRecordBO("acc",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc1",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc2",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc3",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc4",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc5",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc6",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc7",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc8",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc9",100, "txn", "date", "time", "customer"));
            add(new TxnRecordBO("acc10",100, "txn", "date", "time", "customer"));
        }
    };

    @Autowired
    private TxnRecordMapper txnRecordMapper;

    @Override
    public List<TxnRecordBO> getTransaction(String customerId, String accountNumber, String description, int page, int pageSize) {
        Stream<TxnRecordBO> txnStream = txnRecords.stream();

        if(!StringUtil.isBlank(customerId)){
            txnStream = txnStream.filter(t-> t.getCustomerId().equals(customerId));
        }
        if(!StringUtil.isBlank(accountNumber)){
            txnStream = txnStream.filter(t->t.getAccountNumber().equals(accountNumber));
        }
        if(!StringUtil.isBlank(description)){
            txnStream = txnStream.filter(t->t.getDescription().equals(description));
        }

        List<TxnRecordBO> result = txnStream.collect(Collectors.toList());

        int startIndex = page*pageSize;
        int endIndex = (page+1)*pageSize;

        if(result.size() <= startIndex){
            return new ArrayList<>();
        }

        if(result.size() < endIndex){
            endIndex = result.size();
        }

        return result.subList(startIndex, endIndex);
    }

    @Override
    public int getTransactionCount(String customerId, String accountNumber, String description) {
        Stream<TxnRecordBO> txnStream = txnRecords.stream();

        if(!StringUtil.isBlank(customerId)){
            txnStream = txnStream.filter(t-> t.getCustomerId().equals(customerId));
        }
        if(!StringUtil.isBlank(accountNumber)){
            txnStream = txnStream.filter(t->t.getAccountNumber().equals(accountNumber));
        }
        if(!StringUtil.isBlank(description)){
            txnStream = txnStream.filter(t->t.getDescription().equals(description));
        }

        return txnStream.collect(Collectors.toList()).size();
    }

    @Override
    public void insert(TxnRecordBO txnRecordBO) {
        TxnRecord txnRecord = new TxnRecord();
        txnRecord.setAccountnumber(txnRecordBO.getAccountNumber());
        txnRecord.setCustomerId(txnRecordBO.getCustomerId());
        txnRecord.setDescription(txnRecordBO.getDescription());
        txnRecord.setTrxAmount(txnRecordBO.getTrxAmount());
        txnRecord.setTrxDate(txnRecordBO.getTrxDate());
        txnRecord.setTrxTime(txnRecordBO.getTime());
        txnRecordMapper.insert(txnRecord);
        System.out.println(txnRecord);
    }
}