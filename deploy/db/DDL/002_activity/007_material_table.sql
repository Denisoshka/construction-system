CREATE TABLE IF NOT EXISTS material_type
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    manufacturer_id UUID NOT NULL REFERENCES manufacturer_table(id),
    name VARCHAR(150) NOT NULL UNIQUE,
    cost INTEGER      NOT NULL CHECK ( cost > 0 )
)