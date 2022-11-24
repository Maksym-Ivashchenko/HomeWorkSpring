CREATE DATABASE spring_hw WITH OWNER postgres;

CREATE SCHEMA IF NOT EXISTS public;

COMMENT ON SCHEMA PUBLIC IS 'standard public schema';

ALTER schema PUBLIC OWNER TO postgres;

CREATE TABLE users
(
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL
);

ALTER TABLE users OWNER TO postgres;

CREATE TABLE roles
(
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(50) NOT NULL,
	FOREIGN KEY (id) REFERENCES users(id)
);

ALTER TABLE roles OWNER TO postgres;

CREATE TABLE fabricators
(
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(50) NOT NULL UNIQUE 
);

ALTER TABLE fabricators OWNER TO postgres;

CREATE TABLE products
(
	id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(50) NOT NULL,
	price BIGINT,
	fabricator_name VARCHAR(50) NOT NULL,
	FOREIGN KEY (fabricator_name) REFERENCES fabricators(name)
);

ALTER TABLE products OWNER TO postgres;
