CREATE TABLE IF NOT EXISTS school
(
    project_id      UUID PRIMARY KEY REFERENCES construction_project_contract
        (id) ON DELETE CASCADE,
    classroom_count integer NOT NULL,
    floors          integer NOT NULL CHECK ( floors > 0 )
)