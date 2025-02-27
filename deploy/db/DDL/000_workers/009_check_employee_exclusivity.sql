CREATE OR REPLACE FUNCTION fn_check_employee_exclusivity()
    RETURNS TRIGGER AS
$$
BEGIN
    IF EXISTS (SELECT 1 FROM engineers WHERE employee_id = NEW.employee_id)
        OR
       EXISTS (SELECT 1 FROM workers WHERE employee_id = NEW.employee_id) THEN
        RAISE EXCEPTION 'Employee ID % уже существует в одной из таблиц', NEW.employee_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
