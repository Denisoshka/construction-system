CREATE OR REPLACE TRIGGER tr_report_BI
    BEFORE INSERT
    ON report_table
    FOR EACH ROW
EXECUTE PROCEDURE fn_report_set_creation_date()