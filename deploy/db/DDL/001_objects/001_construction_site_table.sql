CREATE TABLE IF NOT EXISTS construction_site
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            VARCHAR(255) UNIQUE NOT NULL,
    address         TEXT                NOT NULL,
    phone           VARCHAR(50) UNIQUE  NOT NULL,
    management_id   UUID
        REFERENCES construction_management (id) ON DELETE CASCADE,
    site_manager_id UUID                NOT NULL
        REFERENCES engineers (employee_id) ON DELETE RESTRICT
)
