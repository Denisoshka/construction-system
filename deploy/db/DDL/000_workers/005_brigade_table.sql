CREATE TABLE IF NOT EXISTS brigade
(
    id         UUID PRIMARY KEY,
    foreman_id UUID NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE
);