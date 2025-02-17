CREATE TABLE IF NOT EXISTS machinery_management
(
    id           SERIAL PRIMARY KEY,
    machinery_id SERIAL NOT NULL REFERENCES machinery_unit (id) ON DELETE CASCADE,
    project_id   SERIAL NOT NULL REFERENCES construction_project (id) ON DELETE CASCADE,
    start_date   DATE   NOT NULL,
    end_date     DATE CHECK (end_date IS NULL OR end_date >= start_date)
)