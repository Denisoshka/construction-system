CREATE TABLE IF NOT EXISTS report_table
(
    id                   serial PRIMARY KEY,
    contract_id          integer NOT NULL REFERENCES construction_project_contract (id) ON DELETE CASCADE,
    report_creation_date date    NOT NULL CHECK ( report_creation_date >= '1900-01-01'::DATE) DEFAULT NOW(),
    report_file          json    NOT NULL
)