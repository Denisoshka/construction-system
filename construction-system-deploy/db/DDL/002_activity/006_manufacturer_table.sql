CREATE TABLE IF NOT EXISTS manufacturer_table
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    manufacturer VARCHAR(150) NOT NULL UNIQUE
)