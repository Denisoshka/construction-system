CREATE TABLE IF NOT EXISTS site_team_table
(
    id           UUID PRIMARY KEY,
    site_head_id UUID NOT NULL REFERENCES engineers (employee_id) ON DELETE CASCADE
);