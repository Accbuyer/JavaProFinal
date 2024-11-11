CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(255),
    possible_payments NUMERIC(10, 2) DEFAULT 10000.00
);