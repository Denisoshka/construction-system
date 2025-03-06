INSERT INTO construction_management(id, name, address, phone,
                                    construction_manager_system_id)
VALUES (default_uuid(), 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', default_uuid())
ON CONFLICT (id) DO NOTHING;

INSERT INTO construction_site(id, name, address, phone, management_id,
                              site_manager_id)
VALUES (default_uuid(), 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', default_uuid(),
        default_uuid())
ON CONFLICT (id) DO NOTHING;

INSERT INTO machinery_type(id, name)
VALUES (default_uuid(), 'UNKNOWN')
ON CONFLICT (id) DO NOTHING;