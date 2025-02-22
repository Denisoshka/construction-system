CREATE TABLE IF NOT EXISTS object_types
(
    type VARCHAR(150) PRIMARY KEY,
    UNIQUE (type)
);