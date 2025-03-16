CREATE TABLE IF NOT EXISTS brigade_management
(
    teamId   UUID NOT NULL REFERENCES brigade (id) ON DELETE CASCADE,
    workerId UUID NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE,
    UNIQUE (workerId),
    PRIMARY KEY (teamId, workerId)
);