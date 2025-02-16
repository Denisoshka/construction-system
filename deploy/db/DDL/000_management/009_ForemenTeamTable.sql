CREATE TABLE IF NOT EXISTS foremen_team_table
(
    teamId     INTEGER NOT NULL REFERENCES foremen_table (id) ON DELETE CASCADE,
    workerId   INTEGER NOT NULL ,
    positionId INTEGER NOT NULL,
    PRIMARY KEY (teamId, workerId, positionId),
    CONSTRAINT fk_worker FOREIGN KEY (workerId, positionId) REFERENCES workers(employee_id, position)
)