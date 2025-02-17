CREATE TABLE IF NOT EXISTS construction_project
(
    id      SERIAL,
    site_id INTEGER NOT NULL REFERENCES construction_site (id) ON DELETE CASCADE,
    PRIMARY KEY (id, site_id)
);
