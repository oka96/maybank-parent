package com.maybank.service;

import com.maybank.model.TxnRecord;
import com.maybank.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TxnRecordServiceImpl implements TxnRecordService {
    List<TxnRecord> txnRecords = new ArrayList(){
        {
            add(new TxnRecord("acc",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc1",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc2",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc3",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc4",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc5",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc6",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc7",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc8",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc9",100, "txn", "date", "time", "customer"));
            add(new TxnRecord("acc10",100, "txn", "date", "time", "customer"));
        }
    };

    @Override
    public List<TxnRecord> getTransaction(String customerId, String accountNumber, String description, int page, int pageSize) {
        Stream<TxnRecord> txnStream = txnRecords.stream();

        if(!StringUtil.isBlank(customerId)){
            txnStream = txnStream.filter(t-> t.getCustomerId().equals(customerId));
        }
        if(!StringUtil.isBlank(accountNumber)){
            txnStream = txnStream.filter(t->t.getAccountNumber().equals(accountNumber));
        }
        if(!StringUtil.isBlank(description)){
            txnStream = txnStream.filter(t->t.getDescription().equals(description));
        }

        List<TxnRecord> result = txnStream.collect(Collectors.toList());

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
        Stream<TxnRecord> txnStream = txnRecords.stream();

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
}