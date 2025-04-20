INSERT INTO worker_position(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO engineer_position(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;