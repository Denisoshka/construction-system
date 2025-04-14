CREATE OR REPLACE TRIGGER check_work_schedule
    BEFORE INSERT OR UPDATE
    ON work_schedule
    FOR EACH ROW
EXECUTE PROCEDURE fn_check_brigade_schedule()