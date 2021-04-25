package com.maybank.config;

import com.maybank.listener.JobCompletionNotificationListener;
import com.maybank.model.RawTxnRecord;
import com.maybank.processor.RawTxnRecordProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${file.input}")
    private String fileInput;

    private int chunkSize = 5;

    @Bean
    public FlatFileItemReader<RawTxnRecord> reader(){
        FlatFileItemReader<RawTxnRecord> reader = new FlatFileItemReader<>();
        DefaultLineMapper<RawTxnRecord> lineMapper = new DefaultLineMapper<RawTxnRecord>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer("|") {
                    {
                        setNames(new String[]{"accountNumber","trxAmount","description","trxDate","trxTime","customerId"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<RawTxnRecord>(){
                    {
                        setTargetType(RawTxnRecord.class);
                    }
                });
            }
        };
        reader.setName("txnRecordReader");
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource(fileInput));
        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter writer(DataSource dataSource){
        String sql = "INSERT INTO txn_record (account_number, trx_amount, description, trx_date, trx_time, customer_id)"
                    + "VALUES (:accountNumber, :trxAmount, :description, :trxDate, :trxTime, :customerId)";
        return new JdbcBatchItemWriterBuilder()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(sql)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public RawTxnRecordProcessor processor(){
        return new RawTxnRecordProcessor();
    }

    @Bean
    public Job importTxnRecordJob(JobCompletionNotificationListener listener, Step step1){
        return jobBuilderFactory.get("importTxnRecordJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<RawTxnRecord> writer){
        return stepBuilderFactory.get("step1")
                .<RawTxnRecord, RawTxnRecord> chunk(chunkSize)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
