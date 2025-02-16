CREATE TABLE IF NOT EXISTS construction_contract
(
    id                       SERIAL PRIMARY KEY,
    customer_organization_id INTEGER      NOT NULL REFERENCES customer_organization (id),
    name                     VARCHAR(150) NOT NULL,
    building_site_id         INTEGER      NOT NULL REFERENCES construction_site (id) ON DELETE NO ACTION,
    signing_date             DATE         NOT NULL CHECK ( signing_date >= to_date('01.01.1900', 'DD-MM-YYYY'))
)