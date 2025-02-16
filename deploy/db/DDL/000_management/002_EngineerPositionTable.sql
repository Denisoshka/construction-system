CREATE TABLE IF NOT EXISTS engineer_position
(
    id   SERIAL PRIMARY KEY,
    name varchar(100) NOT NULL UNIQUE
);