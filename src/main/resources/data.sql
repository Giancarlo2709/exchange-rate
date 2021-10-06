DROP TABLE IF EXISTS exchange_history;
DROP TABLE IF EXISTS exchange_rate;
DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE currency
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    code        VARCHAR(3)   NOT NULL,
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
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    exchange_rate_id      INT,
    amount                DECIMAL(16, 8) NOT NULL,
    exchange_amount       DECIMAL(16, 8) NOT NULL,
    exchange_history_time TIMESTAMP      NOT NULL
);

CREATE TABLE users
(
    user_id    INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status     INT          NOT NULL,
    username   VARCHAR(255) NOT NULL
);

CREATE TABLE roles
(
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NULL
);

CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT unique_user_role_pk UNIQUE (user_id, role_id)
);

ALTER TABLE users_roles ADD CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE users_roles ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles(role_id);

-- Password giancarlo123
INSERT INTO users(username, password, name, last_name, email, status)
VALUES ('giancarlo', '$2a$06$rI58GBM.CnO6oQzY/fuMRulVUZFn..htarqKYfK7aduEHMnDIIQ9m',
        'Giancarlo', 'Yarlequé', 'giancarlo@test.com', 1);

INSERT INTO roles(name)
VALUES ('ADMIN');

INSERT INTO users_roles
VALUES(1, 1);

INSERT INTO currency (code, description)
VALUES ('PEN', 'Soles'),
       ('USD', 'Dólares'),
       ('EUR', 'Euros');

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount)
VALUES ('PEN', 'USD', TO_DATE('05-OCT-2021', 'DD-MON-YYYY'), 0.24);

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount)
VALUES ('USD', 'PEN', TO_DATE('05-OCT-2021', 'DD-MON-YYYY'), 4.12);

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount)
VALUES ('PEN', 'USD', TO_DATE('06-OCT-2021', 'DD-MON-YYYY'), 0.227);

INSERT INTO exchange_rate (currency_code_orig,
                           currency_code_dest, exchange_rate_date, amount)
VALUES ('USD', 'PEN', TO_DATE('06-OCT-2021', 'DD-MON-YYYY'), 4.40);