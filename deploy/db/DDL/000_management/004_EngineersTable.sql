CREATE TABLE IF NOT EXISTS engineers
(
    employee_id SERIAL  NOT NULL REFERENCES employees (id) ON DELETE CASCADE,
    position_id INTEGER NOT NULL REFERENCES engineer_position (id) ON DELETE CASCADE,
    UNIQUE (employee_id, position_id)
)