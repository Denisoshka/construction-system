INSERT INTO customer_organization(id, name)
VALUES (default_uuid(), 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;