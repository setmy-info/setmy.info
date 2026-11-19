-- Run once by the postgres image on an empty volume, as the superuser, after postgres-init.sql (alphabetical order).
-- PostGIS is not a trusted extension: the Liquibase user can not create it, so Liquibase changeset
-- 1.0.0_pg_extension (CREATE EXTENSION IF NOT EXISTS postgis) finds it already here.
\connect springboot_start_app_local
CREATE EXTENSION IF NOT EXISTS postgis;
