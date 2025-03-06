CREATE TABLE IF NOT EXISTS engineers
(
    employee_id UUID    NOT NULL REFERENCES employees (id) ON DELETE CASCADE,
    position_id INTEGER NOT NULL REFERENCES engineer_position (id) ON DELETE CASCADE,
    PRIMARY KEY (employee_id)
);