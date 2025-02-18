CREATE TABLE IF NOT EXISTS foremen_team_management_table
(
    teamId   INTEGER NOT NULL REFERENCES site_team_table (id) ON DELETE CASCADE,
    workerId INTEGER NOT NULL REFERENCES workers (employee_id) ON DELETE CASCADE,
    UNIQUE (workerId),
    PRIMARY KEY (teamId, workerId)
);