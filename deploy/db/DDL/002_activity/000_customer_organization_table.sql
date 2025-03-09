CREATE TABLE IF NOT EXISTS customer_organization
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(100) NOT NULL UNIQUE
)