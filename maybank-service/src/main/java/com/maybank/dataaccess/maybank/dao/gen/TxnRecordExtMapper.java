package com.maybank.dataaccess.maybank.dao.gen;

import com.maybank.dataaccess.maybank.entity.TxnRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TxnRecordExtMapper extends TxnRecordMapper {
    List<TxnRecord> getTransaction(@Param("customerId") String customerId, @Param("accountNumber") String accountNumber,
                                   @Param("description") String description, @Param("start") int start,
                                   @Param("size") int size);

    int getTransactionCount(@Param("customerId") String customerId, @Param("accountNumber") String accountNumber,
                            @Param("description") String description);
}
