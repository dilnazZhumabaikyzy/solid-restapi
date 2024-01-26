CREATE TABLE ACCOUNT (
                         id NVARCHAR(250) PRIMARY KEY,
                         account_type VARCHAR(255),
                         client_id VARCHAR(255),
                         balance DOUBLE,
                         withdraw_allowed BOOLEAN
);


INSERT INTO ACCOUNT (id, account_type, client_id, balance, withdraw_allowed)
VALUES ('001000001', 'SAVING', '1', 1000.0, true);

INSERT INTO ACCOUNT (id, account_type, client_id, balance, withdraw_allowed)
VALUES ('001000002', 'CHECKING', '1', 500.0, true);

INSERT INTO ACCOUNT (id, account_type, client_id, balance, withdraw_allowed)
VALUES ('001000003', 'FIXED', '1', 1500.0, true);

INSERT INTO ACCOUNT (id, account_type, client_id, balance, withdraw_allowed)
VALUES ('002000004', 'CHECKING', '2', 4200.0, true);