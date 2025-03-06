CREATE TABLE IF NOT EXISTS engineers
(
    position_id INTEGER NOT NULL REFERENCES engineer_position (id) ON DELETE SET DEFAULT,
    PRIMARY KEY (id)
) INHERITS (employees);