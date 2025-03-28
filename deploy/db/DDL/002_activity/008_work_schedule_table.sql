CREATE TABLE IF NOT EXISTS work_schedule
(
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    brigade_id      UUID    NOT NULL REFERENCES brigade (id) ON DELETE CASCADE,
    work_type_id    UUID    NOT NULL REFERENCES work_type (id) ON DELETE CASCADE,
    contract_id     UUID    NOT NULL REFERENCES construction_project_contract (id)
        ON DELETE CASCADE,
    plan_start_date DATE    NOT NULL CHECK ( plan_start_date >= '1900-01-01'::DATE),
    plan_end_date   DATE    NOT NULL CHECK (
        plan_end_date >= '1900-01-01'::DATE AND plan_end_date >= plan_start_date
        ),
    plan_order      INTEGER NOT NULL CHECK ( plan_order > 0 )
)