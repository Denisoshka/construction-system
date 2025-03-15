CREATE TABLE IF NOT EXISTS foremen_team_management_table
(
    teamId   UUID NOT NULL REFERENCES brigade (id) ON DELETE CASCADE,
    workerId UUID NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE,
    UNIQUE (workerId),
    PRIMARY KEY (teamId, workerId)
);