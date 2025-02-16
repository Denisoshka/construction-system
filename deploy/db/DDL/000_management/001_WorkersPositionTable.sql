CREATE TABLE IF NOT EXISTS worker_position
(
    id   SERIAL PRIMARY KEY,
    name varchar(100) NOT NULL UNIQUE
);