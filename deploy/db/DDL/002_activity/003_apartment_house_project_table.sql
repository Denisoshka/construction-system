CREATE TABLE IF NOT EXISTS apartment_house
(
    project_id integer PRIMARY KEY REFERENCES construction_project_contract (id) ON DELETE CASCADE,
    floors     integer NOT NULL CHECK ( floors > 0 )
)