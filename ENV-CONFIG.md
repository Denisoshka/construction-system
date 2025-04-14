Для запуска задайте следующие переменные в .env файл или используйте следующие
```
KEYCLOAK_ISSUER_URI=http://localhost:9091/realms/myrealm
KEYCLOAK_SET_URI=http://localhost:9091/realms/myrealm/protocol/openid-connect/certs

CONSTRUCTION_SYSTEM_POSTGRES_DB=construction-system
CONSTRUCTION_SYSTEM_POSTGRES_USER=user
CONSTRUCTION_SYSTEM_POSTGRES_PASSWORD=password
CONSTRUCTION_SYSTEM_DB_URL=jdbc:postgresql://localhost:5433/construction-system

KEYCLOAK_POSTGRES_USER=user
KEYCLOAK_POSTGRES_PASSWORD=password
KEYCLOAK_POSTGRES_DB=keycloak-db
```