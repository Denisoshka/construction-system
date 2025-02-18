CREATE TABLE IF NOT EXISTS construction_site
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    address         TEXT         NOT NULL,
    phone           VARCHAR(50)  NOT NULL,
    management_id   INTEGER REFERENCES construction_management (id) ON DELETE SET DEFAULT,
    site_manager_id INTEGER REFERENCES employees (id) ON DELETE SET DEFAULT
)