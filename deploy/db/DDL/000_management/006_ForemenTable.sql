CREATE TABLE IF NOT EXISTS foremen_table
(
    id       SERIAL PRIMARY KEY,
    workerId INTEGER NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE
)