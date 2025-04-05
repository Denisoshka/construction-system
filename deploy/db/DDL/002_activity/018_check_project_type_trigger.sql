CREATE OR REPLACE TRIGGER enforce_bridge_type
    BEFORE INSERT OR UPDATE ON bridge
    FOR EACH ROW
EXECUTE FUNCTION fn_check_project_type('bridge');

CREATE OR REPLACE TRIGGER enforce_school_type
    BEFORE INSERT OR UPDATE ON school
    FOR EACH ROW
EXECUTE FUNCTION fn_check_project_type('school');

CREATE OR REPLACE TRIGGER enforce_apartment_house_type
    BEFORE INSERT OR UPDATE ON apartment_house
    FOR EACH ROW
EXECUTE FUNCTION fn_check_project_type('apartment_house');