DROP TABLE txn_record IF EXISTS;

CREATE TABLE txn_record (
    txn_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    trx_amount BIGINT NOT NULL,
    description VARCHAR(255) not null,
    trx_date VARCHAR(255),
    trx_time VARCHAR(255),
    customer_id varchar(255) not null
);