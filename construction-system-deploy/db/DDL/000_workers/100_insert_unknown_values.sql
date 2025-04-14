INSERT INTO employees(id, system_id, name,
                      surname, patronymic,
                      employment_date, post)
VALUES (default_uuid(), 'UNKNOWN',
        'UNKNOWN', 'UNKNOWN',
        'UNKNOWN', CURRENT_DATE,
        'UNKNOWN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO worker_position(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO engineer_position(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;