CREATE TABLE IF NOT EXISTS foremen_table
(
    id        SERIAL PRIMARY KEY,
    foremanId integer NOT NULL REFERENCES employees (id) ON DELETE CASCADE
)