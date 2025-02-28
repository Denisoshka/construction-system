INSERT INTO customer_organization(id, name)
VALUES (default_uuid(), 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO object_types (type)
VALUES ('bridge'),
       ('apartment_house'),
       ('school');
