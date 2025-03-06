CREATE TABLE IF NOT EXISTS workers
(
    position_id INTEGER NOT NULL REFERENCES worker_position (id) ON DELETE SET DEFAULT,
    PRIMARY KEY (id)
) INHERITS (employees);