CREATE OR REPLACE FUNCTION fn_check_project_type()
    RETURNS TRIGGER AS $$
DECLARE
    expected_type VARCHAR(150) := TG_ARGV[0];
    expected_type_id INT;
    actual_type_id INT;
BEGIN
    SELECT id INTO expected_type_id
    FROM object_type
    WHERE type = expected_type;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'Type "%" not found in object_type', expected_type;
    END IF;

    SELECT type INTO actual_type_id
    FROM construction_project_contract
    WHERE id = NEW.project_id;

    IF actual_type_id != expected_type_id THEN
        RAISE EXCEPTION 'Type mismatch for table "%". Expected: %, Actual: %',
            TG_TABLE_NAME, expected_type_id, actual_type_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;