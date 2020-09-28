CREATE TABLE PAYMENT_TRANSACTION (
    ID VARCHAR(75),
    AMOUNT DECIMAL (10,2),
    METHOD VARCHAR(100),
    DATE_OCCURRED TIMESTAMP
);

CREATE ALIAS GENERATE_TX_FOR_DATE FOR "site.seedmonkey.generatetestdatah2example.test.H2StoredProcedures.generateTransactionsForDate";