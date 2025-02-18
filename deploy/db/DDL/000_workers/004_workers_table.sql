CREATE TABLE IF NOT EXISTS workers
(
    employee_id INTEGER NOT NULL REFERENCES employees (id) ON DELETE CASCADE,
    position_id    INTEGER NOT NULL REFERENCES worker_position (id) ON DELETE CASCADE,
    PRIMARY KEY (employee_id)
);