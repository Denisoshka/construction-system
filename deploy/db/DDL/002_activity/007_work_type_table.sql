CREATE TABLE IF NOT EXISTS work_type
(
    id   UUID PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
)