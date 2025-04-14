CREATE OR REPLACE FUNCTION fn_check_brigade_schedule()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Проверяем, пересекается ли новый диапазон дат с уже существующими
    IF EXISTS (SELECT 1
               FROM work_schedule
               WHERE brigade_id = NEW.brigade_id
                 AND id <> NEW.id -- Исключаем саму себя при UPDATE
                 AND (
                   (NEW.plan_start_date BETWEEN plan_start_date AND plan_end_date)
                       OR
                   (NEW.plan_end_date BETWEEN plan_start_date AND plan_end_date)
                       OR
                   (plan_start_date BETWEEN NEW.plan_start_date AND NEW.plan_end_date)
                   )) THEN
        RAISE EXCEPTION 'Brigade % is already scheduled for another task during this time!', NEW.brigade_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
