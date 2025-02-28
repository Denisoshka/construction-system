CREATE TABLE IF NOT EXISTS site_head_team_management_table
(
    teamId     UUID NOT NULL REFERENCES site_team_table (id) ON DELETE CASCADE,
    engineerId UUID    NOT NULL REFERENCES engineers (employee_id) ON DELETE CASCADE,
    UNIQUE (engineerId),
    PRIMARY KEY (teamId, engineerId)
);