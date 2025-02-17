CREATE TABLE IF NOT EXISTS estimate_table
(
    id               INTEGER PRIMARY KEY REFERENCES construction_project_contract (id),
    last_update_date DATE NOT NULL CHECK (last_update_date >= '1900-01-01'::DATE) DEFAULT NOW()
)