CREATE TABLE IF NOT EXISTS work_schedule
(
    id              serial PRIMARY KEY,
    brigade_id      INTEGER NOT NULL REFERENCES brigade_table (id) ON DELETE CASCADE,
    work_type_id    INTEGER NOT NULL REFERENCES work_type (id) ON DELETE CASCADE,
    plan_start_date DATE    NOT NULL CHECK ( plan_start_date >= '1900-01-01'::DATE),
    plan_end_date   DATE CHECK (
        plan_end_date >= '1900-01-01'::DATE AND plan_end_date >= plan_start_date
        ),
    fact_start_date DATE CHECK (
        fact_start_date >= '1900-01-01'::DATE
        ),
    fact_end_date   DATE CHECK (
        fact_end_date >= '1900-01-01'::DATE AND fact_end_date >= fact_start_date
        ),
    plan_order      INTEGER NOT NULL CHECK ( plan_order > 0 ),
    fact_order      INTEGER CHECK ( fact_order > 0 ),
    contract_id     INTEGER REFERENCES construction_project_contract (id) ON DELETE CASCADE
)