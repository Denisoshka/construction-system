CREATE TABLE IF NOT EXISTS construction_project_contract
(
    id                       SERIAL PRIMARY KEY,
    project_id               INTEGER      NOT NULL,
    site_id                  INTEGER      NOT NULL,
    customer_organization_id INTEGER      NOT NULL REFERENCES customer_organization (id) ON DELETE NO ACTION,
    name                     VARCHAR(150) NOT NULL,
    signing_date             DATE         NOT NULL CHECK (
        signing_date >= '1900-01-01'::DATE
        ),
    plan_start_date          date         NOT NULL CHECK (
        plan_start_date >= '1900-01-01'::DATE AND signing_date >= plan_start_date
        ),
    plan_end_date            date CHECK (
        plan_end_date >= '1900-01-01'::DATE AND plan_end_date >= plan_start_date),
    fact_start_date          date CHECK ( fact_start_date >= '1900-01-01'::DATE),
    fact_end_date            date CHECK (
        fact_end_date >= '1900-01-01'::DATE AND fact_end_date >= fact_start_date
        ),
    FOREIGN KEY (project_id, site_id) REFERENCES construction_project (id, site_id) ON DELETE NO ACTION
);
