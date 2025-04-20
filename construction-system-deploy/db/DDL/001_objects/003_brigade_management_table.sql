CREATE TABLE IF NOT EXISTS brigade_management
(
    team_id   UUID NOT NULL REFERENCES brigade (id) ON DELETE CASCADE,
    worker_id UUID NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE,
    PRIMARY KEY (worker_id)
);