CREATE TABLE IF NOT EXISTS construction_project_contract
(
    id                       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id               UUID    NOT NULL DEFAULT gen_random_uuid(),
    site_id                  UUID    NOT NULL,
    customer_organization_id UUID    NOT NULL REFERENCES customer_organization
        (id) ON DELETE CASCADE,
    type                     INTEGER NOT NULL REFERENCES object_types (id)
        ON DELETE RESTRICT,
    date_of_creation         DATE    NOT NULL CHECK (
        signing_date >= '1900-01-01'::DATE
        )                                     DEFAULT now(),
    signing_date             DATE CHECK (
        signing_date >= '1900-01-01'::DATE
        ),
    acceptance_date          DATE CHECK ( acceptance_date >= signing_date),
    plan_start_date          DATE    NOT NULL CHECK (
        plan_start_date >= '1900-01-01'::DATE AND
        signing_date >= plan_start_date
        ),
    plan_end_date            DATE CHECK (
        plan_end_date >= '1900-01-01'::DATE AND
        plan_end_date >= plan_start_date),
    fact_start_date          DATE CHECK ( fact_start_date >= '1900-01-01'::DATE),
    fact_end_date            DATE CHECK (
        fact_end_date >= '1900-01-01'::DATE AND fact_end_date >= fact_start_date
        ),
    FOREIGN KEY (project_id, site_id) REFERENCES construction_project (id, site_id) ON DELETE CASCADE
);
