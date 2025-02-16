CREATE TABLE IF NOT EXISTS foremen
(
    foremanId integer NOT NULL REFERENCES employees (id),
    profileId integer NOT NULL REFERENCES worker_position (id)
)