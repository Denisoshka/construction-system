CREATE TABLE IF NOT EXISTS site_team_management
(
    site_id     UUID NOT NULL REFERENCES construction_site (id) ON
        DELETE CASCADE,
    engineer_id UUID NOT NULL REFERENCES engineers (employee_id) ON DELETE CASCADE,
    PRIMARY KEY (engineer_id)
);