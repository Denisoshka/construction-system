CREATE TABLE IF NOT EXISTS machinery_management
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(150) NOT NULL,
    machinery_id SERIAL       NOT NULL REFERENCES machinery_type (id),
    project_id   SERIAL       NOT NULL REFERENCES construction_project (id)
)