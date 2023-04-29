CREATE DATABASE mydb;
USE mydb;
CREATE USER myuser WITH PASSWORD 'mypassword';
ALTER DATABASE mydb OWNER TO myuser;
-- In under postgres user for mydb
CREATE EXTENSION postgis;
CREATE EXTENSION postgis_topology;
CREATE EXTENSION fuzzystrmatch;
CREATE EXTENSION postgis_tiger_geocoder;
