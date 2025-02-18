CREATE TABLE IF NOT EXISTS estimate
(
    id               INTEGER PRIMARY KEY REFERENCES construction_project_contract (id) ON DELETE CASCADE,
    last_update_date DATE NOT NULL CHECK (last_update_date >= '1900-01-01'::DATE) DEFAULT NOW()
)