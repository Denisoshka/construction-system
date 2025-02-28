CREATE TABLE IF NOT EXISTS construction_project_contract
(
    id                       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id               UUID         NOT NULL,
    site_id                  UUID         NOT NULL,
    customer_organization_id UUID         NOT NULL REFERENCES customer_organization
        (id) ON DELETE SET DEFAULT            DEFAULT default_uuid(),
    type                     VARCHAR(150) NOT NULL REFERENCES object_types
        (type) ON DELETE NO ACTION,
    signing_date             DATE         NOT NULL CHECK (
        signing_date >= '1900-01-01'::DATE
        ),
    plan_start_date          date         NOT NULL CHECK (
        plan_start_date >= '1900-01-01'::DATE AND
        signing_date >= plan_start_date
        ),
    plan_end_date            date CHECK (
        plan_end_date >= '1900-01-01'::DATE AND
        plan_end_date >= plan_start_date),
    fact_start_date          date CHECK ( fact_start_date >= '1900-01-01'::DATE),
    fact_end_date            date CHECK (
        fact_end_date >= '1900-01-01'::DATE AND fact_end_date >= fact_start_date
        ),
    FOREIGN KEY (project_id, site_id) REFERENCES construction_project (id, site_id) ON DELETE CASCADE
);
