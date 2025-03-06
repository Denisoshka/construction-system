CREATE TABLE IF NOT EXISTS construction_site
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            VARCHAR(255) NOT NULL,
    address         TEXT         NOT NULL,
    phone           VARCHAR(50)  NOT NULL,
    management_id   UUID         REFERENCES construction_management (id) ON DELETE SET NULL,
    site_manager_id UUID         REFERENCES employees (id) ON DELETE SET NULL
)
