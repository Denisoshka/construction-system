CREATE TYPE job_post AS ENUM ('UNKNOWN', 'ENGINEER', 'WORKER');

CREATE TABLE IF NOT EXISTS employees
(
    id              UUID PRIMARY KEY             DEFAULT gen_random_uuid(),
    system_id       VARCHAR(100) UNIQUE NOT NULL,
    name            VARCHAR(100)        NOT NULL,
    surname         VARCHAR(100)        NOT NULL,
    patronymic      VARCHAR(100)        NOT NULL,
    employment_date DATE                NOT NULL,
    post            job_post            NOT NULL DEFAULT 'UNKNOWN'
);

CREATE FUNCTION default_uuid() RETURNS UUID AS
$$
BEGIN
    RETURN '00000000-0000-0000-0000-000000000000'::UUID;
END;
$$ LANGUAGE plpgsql IMMUTABLE;


