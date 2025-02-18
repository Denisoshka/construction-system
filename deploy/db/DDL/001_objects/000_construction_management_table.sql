CREATE TABLE IF NOT EXISTS construction_management
(
    id                      SERIAL PRIMARY KEY,
    name                    VARCHAR(100) NOT NULL,
    address                 TEXT         NOT NULL,
    phone                   VARCHAR(50)  NOT NULL,
    construction_manager_id SERIAL REFERENCES engineers (employee_id) ON DELETE SET DEFAULT
)