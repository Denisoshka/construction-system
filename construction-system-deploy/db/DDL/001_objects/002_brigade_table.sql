CREATE TABLE IF NOT EXISTS brigade
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    foreman_id UUID NOT NULL REFERENCES workers (employee_id) ON DELETE
        CASCADE,
    site_id    UUID NOT NULL REFERENCES construction_site (id) ON DELETE CASCADE
);