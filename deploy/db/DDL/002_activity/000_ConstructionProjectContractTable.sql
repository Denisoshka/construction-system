CREATE TABLE IF NOT EXISTS construction_project_contract
(
    id                       SERIAL PRIMARY KEY,
    customer_organization_id INTEGER      NOT NULL REFERENCES customer_organization (id) ON DELETE NO ACTION,
    name                     VARCHAR(150) NOT NULL,
    building_site_id         INTEGER      NOT NULL REFERENCES construction_site (id) ON DELETE NO ACTION,
    signing_date             DATE         NOT NULL CHECK (signing_date >= '1900-01-01'::DATE)
)