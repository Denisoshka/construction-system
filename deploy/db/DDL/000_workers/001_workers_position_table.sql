CREATE TABLE IF NOT EXISTS worker_position
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);