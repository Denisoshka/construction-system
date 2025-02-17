CREATE TABLE IF NOT EXISTS report_table
(
    id                   serial PRIMARY KEY,
    contract_id          integer NOT NULL REFERENCES construction_project_contract (id),
    report_creation_date date    NOT NULL CHECK ( report_creation_date >= to_date('01.01.1900', 'DD-MM-YYYY')) DEFAULT NOW(),
    report_file          json    NOT NULL
)