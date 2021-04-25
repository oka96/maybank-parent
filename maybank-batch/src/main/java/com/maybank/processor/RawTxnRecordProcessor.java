package com.maybank.processor;

import com.maybank.model.RawTxnRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class RawTxnRecordProcessor implements ItemProcessor<RawTxnRecord, RawTxnRecord> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawTxnRecordProcessor.class);

    @Override
    public RawTxnRecord process(RawTxnRecord item) throws Exception {
//        String record = "('"+item.getAccountNumber()+"',"
//                +item.getTrxAmount()+",'"
//                +item.getDescription()+"','"
//                +item.getTrxDate()+"','"
//                +item.getTrxTime()+"','"
//                +item.getCustomerId()+"'),";
//        System.out.println(record);

        LOGGER.info("Processing {}", item.getAccountNumber());
        return item;
    }
}
