CREATE TABLE IF NOT EXISTS workers
(
    employee_id SERIAL NOT NULL REFERENCES employees (id) ON DELETE CASCADE,
    position   SERIAL NOT NULL REFERENCES worker_position (id) ON DELETE SET DEFAULT,
    PRIMARY KEY (employee_id, position)
)