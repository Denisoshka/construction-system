CREATE TABLE IF NOT EXISTS foremen_table
(
    id        SERIAL PRIMARY KEY,
    site_head INTEGER NOT NULL REFERENCES foremen_table (id) ON DELETE SET DEFAULT,
    PRIMARY KEY (id, site_head)
)