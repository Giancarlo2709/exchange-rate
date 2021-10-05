DROP TABLE IF EXISTS exchange_history;
DROP TABLE IF EXISTS exchange_rate;
DROP TABLE IF EXISTS currency;

CREATE TABLE currency
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(3)   NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE exchange_rate
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    currency_code_orig VARCHAR(3)     NOT NULL,
    currency_code_dest VARCHAR(3)     NOT NULL,
    exchange_rate_date DATE           NOT NULL,
    amount             DECIMAL(16, 8) NOT NULL
);

CREATE TABLE exchange_history
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    exchange_rate_id     INT,
    amount               DECIMAL(16, 8) NOT NULL,
    exchange_amount     DECIMAL(16, 8) NOT NULL,
    exchange_history_time TIMESTAMP      NOT NULL
);

INSERT INTO currency (code, description) VALUES
('PEN', 'Soles'),
('USD', 'Dólares'),
('EUR', 'Euros');

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount) VALUES
('PEN', 'USD', TO_DATE('05-OCT-2021', 'DD-MON-YYYY'), 0.24);

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount) VALUES
('USD', 'PEN', TO_DATE('05-OCT-2021', 'DD-MON-YYYY'), 4.12);

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount) VALUES
('PEN', 'USD', TO_DATE('06-OCT-2021', 'DD-MON-YYYY'), 0.227);

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount) VALUES
('USD', 'PEN', TO_DATE('06-OCT-2021', 'DD-MON-YYYY'), 4.40);