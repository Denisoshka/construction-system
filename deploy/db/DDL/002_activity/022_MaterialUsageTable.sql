CREATE TABLE IF NOT EXISTS material_usage
(
    estimate_id   integer REFERENCES estimate_table (id),
    material_id   integer REFERENCES ma (id),
    plan_quantity integer CHECK ( plan_quantity > 0 ),
    fact_quantity integer NOT NULL CHECK ( fact_quantity > 0 ),
    PRIMARY KEY (estimate_id, material_id)
)