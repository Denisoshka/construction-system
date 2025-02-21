CREATE TABLE IF NOT EXISTS employees
(
    id              SERIAL PRIMARY KEY,
    system_id       VARCHAR(100) NOT NULL,
    name            VARCHAR(100) NOT NULL,
    surname         VARCHAR(100) NOT NULL,
    patronymic      VARCHAR(100) NOT NULL,
    employment_date DATE         NOT NULL,
    post            VARCHAR(100) NOT NULL,
    CHECK (post IN ('engineer', 'worker')),
    UNIQUE (system_id)
);
