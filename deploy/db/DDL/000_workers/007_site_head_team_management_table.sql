CREATE TABLE IF NOT EXISTS site_head_team_management_table
(
    teamId     INTEGER NOT NULL REFERENCES site_team_table (id) ON DELETE CASCADE,
    engineerId INTEGER NOT NULL REFERENCES engineers (employee_id) ON DELETE CASCADE,
    UNIQUE (engineerId),
    PRIMARY KEY (teamId, engineerId)
);