CREATE TABLE IF NOT EXISTS material_usage
(
    work_unit_id  UUID REFERENCES work_schedule (id) ON DELETE CASCADE,
    material_id   UUID REFERENCES material_type (id) ON DELETE CASCADE,
    plan_quantity INTEGER NOT NULL CHECK ( plan_quantity > 0 ),
    fact_quantity INTEGER CHECK ( fact_quantity > 0 ),
    PRIMARY KEY (work_unit_id, material_id)
)