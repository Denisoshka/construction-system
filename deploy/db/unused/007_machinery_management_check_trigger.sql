CREATE TRIGGER trigger_check_date_overlap
    BEFORE INSERT OR UPDATE ON machinery_management
    FOR EACH ROW
EXECUTE FUNCTION check_date_overlap();
