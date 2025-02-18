-- Создаем функцию, которая будет проверять перекрытие дат
CREATE OR REPLACE FUNCTION check_date_overlap()
    RETURNS TRIGGER AS $$
BEGIN
    -- Проверяем, есть ли запись с тем же machinery_id и перекрывающимся интервалом
    IF EXISTS (
        SELECT 1
        FROM machinery_management
        WHERE machinery_id = NEW.machinery_id
          AND ((NEW.start_date BETWEEN start_date AND end_date) OR (NEW.end_date BETWEEN start_date AND end_date))
          AND id <> NEW.id  -- исключаем проверку на текущую вставляемую запись
    ) THEN
        RAISE EXCEPTION 'Date range overlaps with existing entry for machinery_id %', NEW.machinery_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;