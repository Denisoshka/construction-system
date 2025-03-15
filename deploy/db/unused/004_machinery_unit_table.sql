CREATE TABLE IF NOT EXISTS machinery_unit
(
    id            uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    type_id       uuid         REFERENCES machinery_type (id) ON DELETE
        SET NULL,
    management_id uuid         REFERENCES construction_management (id) ON
        DELETE SET NULL,
    manufacturer  VARCHAR(150) NOT NULL,
    model         VARCHAR(150) NOT NULL,
    vin_number    VARCHAR(17) UNIQUE CHECK (LENGTH(vin_number) = 17)
);