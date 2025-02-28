CREATE TABLE IF NOT EXISTS machinery_type
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(150) NOT NULL
)