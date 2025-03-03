CREATE TABLE IF NOT EXISTS construction_project
(
    id      UUID NOT NULL DEFAULT gen_random_uuid(),
    site_id UUID NOT NULL REFERENCES construction_site (id) ON DELETE CASCADE,
    PRIMARY KEY (id, site_id)
);
