CREATE TABLE IF NOT EXISTS site_head_team_table
(
    teamID     INTEGER NOT NULL REFERENCES site_head_table (id) ON DELETE NO ACTION,
    employeeId INTEGER NOT NULL REFERENCES employees (id) ON DELETE NO ACTION,
    PRIMARY KEY (teamID, employeeId)
)