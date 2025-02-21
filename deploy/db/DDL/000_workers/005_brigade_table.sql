CREATE TABLE IF NOT EXISTS brigade
(
    id         SERIAL PRIMARY KEY,
    foreman_id INTEGER NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE
);