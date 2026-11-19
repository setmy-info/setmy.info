-- Generated: SMI_DB_APP_PASSWORD=g6p8 SMI_DB_LIQUIBASE_PASSWORD=g6p8 smi-postgresql-create-db-sql springboot_start_app local > postgres-init.sql
CREATE USER localspringboot_start_app;
CREATE USER localspringboot_start_appliquibase;
ALTER USER localspringboot_start_app WITH PASSWORD 'g6p8';
ALTER USER localspringboot_start_appliquibase WITH PASSWORD 'g6p8';
CREATE DATABASE springboot_start_app_local WITH TEMPLATE = template0 ENCODING = 'UTF8';
ALTER DATABASE springboot_start_app_local OWNER TO localspringboot_start_appliquibase;
REVOKE ALL ON DATABASE springboot_start_app_local FROM PUBLIC;
GRANT CONNECT, TEMPORARY ON DATABASE springboot_start_app_local TO localspringboot_start_appliquibase;
GRANT CONNECT ON DATABASE springboot_start_app_local TO localspringboot_start_app;
\connect springboot_start_app_local
ALTER SCHEMA public OWNER TO localspringboot_start_appliquibase;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT USAGE, CREATE ON SCHEMA public TO localspringboot_start_appliquibase;
GRANT USAGE ON SCHEMA public TO localspringboot_start_app;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO localspringboot_start_app;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO localspringboot_start_app;
ALTER DEFAULT PRIVILEGES FOR USER localspringboot_start_appliquibase IN SCHEMA public GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO localspringboot_start_app;
ALTER DEFAULT PRIVILEGES FOR USER localspringboot_start_appliquibase IN SCHEMA public GRANT USAGE ON SEQUENCES TO localspringboot_start_app;
ALTER DEFAULT PRIVILEGES FOR USER localspringboot_start_appliquibase REVOKE EXECUTE ON FUNCTIONS FROM PUBLIC;
ALTER DEFAULT PRIVILEGES FOR USER localspringboot_start_appliquibase IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO localspringboot_start_app;
