CREATE TABLE IF NOT EXISTS site_head_team_management_table
(
    teamId     INTEGER NOT NULL REFERENCES site_head_table (id) ON DELETE CASCADE,
    engineerId INTEGER NOT NULL REFERENCES engineers (employee_id) ON DELETE NO ACTION,
    UNIQUE (engineerId),
    PRIMARY KEY (teamId, engineerId)
)