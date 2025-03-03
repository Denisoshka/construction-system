CREATE TABLE IF NOT EXISTS material_type
(
    id   UUID PRIMARY KEY,
    name VARCHAR(150) NOT NULL UNIQUE,
    cost INTEGER      NOT NULL CHECK ( cost > 0 )
)