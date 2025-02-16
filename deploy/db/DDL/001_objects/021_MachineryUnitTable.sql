CREATE TABLE IF NOT EXISTS machinery_unit
(
    id      SERIAL PRIMARY KEY,
    type_id SERIAL       NOT NULL REFERENCES machinery_type (id),
    name    VARCHAR(150) NOT NULL
)