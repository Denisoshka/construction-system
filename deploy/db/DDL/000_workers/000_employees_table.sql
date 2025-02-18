CREATE TABLE IF NOT EXISTS employees
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    surname    VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100) NOT NULL,
    seniority  DATE         NOT NULL
);
