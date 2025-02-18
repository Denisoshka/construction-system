CREATE TABLE IF NOT EXISTS material_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cost INTEGER      NOT NULL CHECK ( cost > 0 )
)