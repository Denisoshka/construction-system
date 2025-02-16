CREATE TABLE IF NOT EXISTS site_head_team_table
(
    teamID     INTEGER NOT NULL REFERENCES site_head_table (id) ON DELETE CASCADE,
    engineerId INTEGER NOT NULL, -- Ссылается на employee_id
    positionId INTEGER NOT NULL, -- Ссылается на position_id
    PRIMARY KEY (teamID, engineerId, positionId),
    CONSTRAINT fk_engineer FOREIGN KEY (engineerId, positionId) REFERENCES engineers (employee_id, position_id) ON DELETE NO ACTION
)