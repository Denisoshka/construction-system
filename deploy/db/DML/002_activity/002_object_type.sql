INSERT INTO object_type (type)
VALUES
    ('bridge'),
    ('school'),
    ('apartment_house')
ON CONFLICT (type) DO NOTHING;