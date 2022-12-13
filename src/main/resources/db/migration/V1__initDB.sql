CREATE TABLE IF NOT EXISTS users
(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	user_name VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	role_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles(
	user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS fabricators
(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	fabricator_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS products
(
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	product_name VARCHAR(50) NOT NULL,
	price BIGINT,
	fabricator_id UUID,
	FOREIGN KEY (fabricator_id) REFERENCES fabricators(id)
);
