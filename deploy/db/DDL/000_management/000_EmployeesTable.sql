CREATE TABLE IF NOT EXISTS employees
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    surname    VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100) NOT NULL,
    age        INTEGER      NOT NULL CHECK (age > 0),
    seniority  DATE         NOT NULL
);
