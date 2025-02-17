CREATE TABLE IF NOT EXISTS construction_management
(
    id                      SERIAL PRIMARY KEY,
    name                    VARCHAR(100) NOT NULL,
    address                 TEXT,
    phone                   VARCHAR(50),
    construction_manager_id SERIAL REFERENCES employees (id) ON DELETE SET DEFAULT
)