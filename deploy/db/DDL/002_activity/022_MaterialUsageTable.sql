CREATE TABLE IF NOT EXISTS material_usage
(
    estimate_id   INTEGER REFERENCES estimate_table (id),
    material_id   INTEGER REFERENCES machinery_type (id),
    plan_quantity INTEGER NOT NULL CHECK ( plan_quantity > 0 ),
    fact_quantity INTEGER CHECK ( fact_quantity > 0 ),
    PRIMARY KEY (estimate_id, material_id)
)