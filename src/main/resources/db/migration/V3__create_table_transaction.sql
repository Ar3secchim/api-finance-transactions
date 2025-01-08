CREATE TABLE transactions (
    id VARCHAR(50) NOT NULL PRIMARY KEY,
    origin_account VARCHAR(50) NOT NULL,
    destiny_account VARCHAR(50) NOT NULL,
    value DECIMAL(15,2) NOT NULL,
    at_created DATETIME NOT NULL
);