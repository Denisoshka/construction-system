CREATE TABLE IF NOT EXISTS work_type
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(100) UNIQUE NOT NULL
)