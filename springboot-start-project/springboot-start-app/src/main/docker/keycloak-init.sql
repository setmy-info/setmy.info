-- Generated: SMI_DB_APP_PASSWORD=g6p8 SMI_DB_LIQUIBASE_PASSWORD=g6p8 smi-postgresql-create-db-sql keycloak local > keycloak-init.sql
CREATE USER localkeycloak;
CREATE USER localkeycloakliquibase;
ALTER USER localkeycloak WITH PASSWORD 'g6p8';
ALTER USER localkeycloakliquibase WITH PASSWORD 'g6p8';
CREATE DATABASE keycloak_local WITH TEMPLATE = template0 ENCODING = 'UTF8';
ALTER DATABASE keycloak_local OWNER TO localkeycloakliquibase;
REVOKE ALL ON DATABASE keycloak_local FROM PUBLIC;
GRANT CONNECT, TEMPORARY ON DATABASE keycloak_local TO localkeycloakliquibase;
GRANT CONNECT ON DATABASE keycloak_local TO localkeycloak;
\connect keycloak_local
ALTER SCHEMA public OWNER TO localkeycloakliquibase;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT USAGE, CREATE ON SCHEMA public TO localkeycloakliquibase;
GRANT USAGE ON SCHEMA public TO localkeycloak;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO localkeycloak;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO localkeycloak;
ALTER DEFAULT PRIVILEGES FOR USER localkeycloakliquibase IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO localkeycloak;
ALTER DEFAULT PRIVILEGES FOR USER localkeycloakliquibase IN SCHEMA public GRANT USAGE ON SEQUENCES TO localkeycloak;
ALTER DEFAULT PRIVILEGES FOR USER localkeycloakliquibase REVOKE EXECUTE ON FUNCTIONS FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR USER localkeycloakliquibase IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO localkeycloak;
