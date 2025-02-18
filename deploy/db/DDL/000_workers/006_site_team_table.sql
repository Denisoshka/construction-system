CREATE TABLE IF NOT EXISTS site_team_table
(
    id           SERIAL PRIMARY KEY,
    site_head_id INTEGER NOT NULL REFERENCES employees (id) ON DELETE CASCADE
)