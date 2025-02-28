CREATE TABLE IF NOT EXISTS machinery_management
(
    id           UUID PRIMARY KEY,
    machinery_id UUID    NOT NULL REFERENCES machinery_unit (id) ON DELETE CASCADE,
    site_id      UUID NOT NULL,
    project_id   UUID NOT NULL,
    start_date   DATE    NOT NULL,
    end_date     DATE CHECK (end_date IS NULL OR end_date >= start_date),
    FOREIGN KEY (project_id, site_id) REFERENCES construction_project (id, site_id) ON DELETE CASCADE
)