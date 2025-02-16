CREATE TABLE IF NOT EXISTS engineers
(
    employee_id SERIAL  NOT NULL REFERENCES employees (id) ON DELETE CASCADE,
    position_id integer NOT NULL REFERENCES engineer_position (id) ON DELETE SET DEFAULT,
    UNIQUE (employee_id, position_id)
)