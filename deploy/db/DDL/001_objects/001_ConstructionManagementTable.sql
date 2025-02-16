CREATE TABLE IF NOT EXISTS construction_management
(
    id                    serial PRIMARY KEY,
    name                  varchar(100) NOT NULL,
    address               TEXT,
    phone                 VARCHAR(50),
    construction_manager_id SERIAL REFERENCES employees (id)
)