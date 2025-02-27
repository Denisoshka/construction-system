CREATE TRIGGER trg_check_employee_in_engineers
    BEFORE INSERT
    ON engineers
    FOR EACH ROW
EXECUTE FUNCTION fn_check_employee_exclusivity();

CREATE TRIGGER trg_check_employee_in_workers
    BEFORE INSERT
    ON workers
    FOR EACH ROW
EXECUTE FUNCTION fn_check_employee_exclusivity();
