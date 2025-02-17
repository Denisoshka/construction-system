CREATE TABLE IF NOT EXISTS construction_site
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    address         TEXT,
    phone           VARCHAR(50),
    management_id   INTEGER REFERENCES construction_management (id) ON DELETE CASCADE,
    site_manager_id INTEGER      REFERENCES employees (id) ON DELETE SET NULL,
    UNIQUE (site_manager_id)
)