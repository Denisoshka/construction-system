CREATE TABLE IF NOT EXISTS site_team_management
(
    teamId     UUID NOT NULL REFERENCES construction_site (id) ON
        DELETE CASCADE,
    engineerId UUID NOT NULL REFERENCES engineers (employee_id) ON DELETE CASCADE,
    PRIMARY KEY (engineerId)
);