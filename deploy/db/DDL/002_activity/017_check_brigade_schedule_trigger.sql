CREATE OR REPLACE TRIGGER tr_work_schedule_BI_BU
    BEFORE INSERT OR UPDATE
    ON work_schedule
    FOR EACH ROW
EXECUTE PROCEDURE fn_check_brigade_schedule()