INSERT INTO employees (id, name, surname, patronymic, seniority)
VALUES (0, 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', CURRENT_DATE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO worker_position(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO engineer_position(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO engineers(employee_id, position_id)
VALUES (0, 0)
ON CONFLICT (employee_id) DO NOTHING;

INSERT INTO workers(employee_id, position_id)
VALUES (0, 0)
ON CONFLICT (employee_id) DO NOTHING;
