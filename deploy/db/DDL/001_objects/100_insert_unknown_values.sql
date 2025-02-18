INSERT INTO construction_management(id, name, address, phone, construction_manager_id)
VALUES (0, 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 0)
ON CONFLICT (id) DO NOTHING;

INSERT INTO construction_site(id, name, address, phone, management_id, site_manager_id)
VALUES (0, 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 0, 0)
ON CONFLICT (id) DO NOTHING;

INSERT INTO machinery_type(id, name)
VALUES (0, 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;