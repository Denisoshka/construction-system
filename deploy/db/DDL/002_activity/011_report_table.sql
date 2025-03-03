CREATE TABLE IF NOT EXISTS report_table
(
    contract_id          UUID PRIMARY KEY REFERENCES construction_project_contract (id) ON DELETE CASCADE,
    report_creation_date DATE  NOT NULL CHECK ( report_creation_date >=
                                                '1900-01-01'::DATE) DEFAULT NOW(),
    report_file          JSONB NOT NULL
)