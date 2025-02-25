CREATE TABLE IF NOT EXISTS machinery_unit
(
    id            SERIAL PRIMARY KEY,
    type_id       INTEGER      NOT NULL REFERENCES machinery_type (id) ON DELETE SET DEFAULT,
    management_id INTEGER      NOT NULL REFERENCES construction_management (id) ON DELETE SET DEFAULT,
    manufacturer  VARCHAR(150) NOT NULL,
    model         VARCHAR(150) NOT NULL,
    vin_number    VARCHAR(17) CHECK (LENGTH(vin_number) = 17)
);