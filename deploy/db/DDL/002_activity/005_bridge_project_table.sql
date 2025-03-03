CREATE TABLE IF NOT EXISTS bridge
(
    project_id           UUID PRIMARY KEY REFERENCES
        construction_project_contract (id) ON DELETE CASCADE,
    span                 INTEGER NOT NULL CHECK ( span > 0 ),
    width                INTEGER NOT NULL CHECK ( width > 0 ),
    traffic_lanes_number INTEGER NOT NULL CHECK ( traffic_lanes_number > 0 )
)