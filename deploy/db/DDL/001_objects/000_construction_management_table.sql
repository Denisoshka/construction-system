CREATE TABLE IF NOT EXISTS construction_management
(
    id                      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name                    VARCHAR(100) UNIQUE NOT NULL,
    address                 TEXT                NOT NULL,
    phone                   VARCHAR(50) UNIQUE  NOT NULL,
    construction_manager_id UUID                NOT NULL
        REFERENCES engineers (employee_id) ON DELETE RESTRICT
)