CREATE OR REPLACE FUNCTION enforce_job_post_rules()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.post = 'UNKNOWN' THEN
        DELETE FROM engineers WHERE employee_id = OLD.id;
        DELETE FROM workers WHERE employee_id = OLD.id;
    END IF;

    IF OLD.post <> 'UNKNOWN' AND NEW.post <> 'UNKNOWN' THEN
        RAISE EXCEPTION 'Можно менять только на UNKNOWN';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION set_post_unknown()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE employees
    SET post = 'UNKNOWN'
    WHERE id = OLD.employee_id;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_enforce_job_post_rules ON employees;
DROP TRIGGER IF EXISTS update_post_on_delete_engineers ON engineers;
DROP TRIGGER IF EXISTS update_post_on_delete_workers ON workers;

CREATE TRIGGER trigger_enforce_job_post_rules
    BEFORE UPDATE
    ON employees
    FOR EACH ROW
EXECUTE FUNCTION enforce_job_post_rules();

CREATE TRIGGER update_post_on_delete_engineers
    BEFORE DELETE
    ON engineers
    FOR EACH ROW
EXECUTE FUNCTION set_post_unknown();

CREATE TRIGGER update_post_on_delete_workers
    BEFORE DELETE
    ON workers
    FOR EACH ROW
EXECUTE FUNCTION set_post_unknown();
