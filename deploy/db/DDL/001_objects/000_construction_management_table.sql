CREATE TABLE IF NOT EXISTS construction_management
(
    id                      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name                    VARCHAR(100) NOT NULL,
    address                 TEXT         NOT NULL,
    phone                   VARCHAR(50)  NOT NULL,
    construction_manager_id UUID         REFERENCES engineers (employee_id) ON DELETE
        SET NULL
)