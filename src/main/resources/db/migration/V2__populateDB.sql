INSERT INTO users(user_name, password, first_name, last_name)
VALUES('yoda', '$2a$10$PLaC/RjXYFKWwk0gckMKa.iM2URT15qCe/Ho01K1F/lVq26dAki7a', 'Ivan', 'Ivanov');
INSERT INTO users(user_name, password, first_name, last_name)
VALUES('obivan', '$2a$10$YIro7gy91DP9loZbo.75yOspubnuw0aH5ZE/bGsH.Z5uHIWnnIRPa', 'Obi', 'Van');
INSERT INTO users(user_name, password, first_name, last_name)
VALUES('veider', '$2a$10$/MmquLQXZeT3rjiKnODwDuuXlUFFe7GpBD398b4ohX/LlmfiaAz/K', 'Dart', 'Veider');

INSERT INTO roles(role_name) VALUES('USER');
INSERT INTO roles(role_name) VALUES('ADMIN');

INSERT INTO fabricators(fabricator_name) VALUES('Lenivec');
INSERT INTO fabricators(fabricator_name) VALUES('Sonic');
INSERT INTO fabricators(fabricator_name) VALUES('Bufet');
INSERT INTO fabricators(fabricator_name) VALUES('Sladik');

INSERT INTO users_roles VALUES
(
(SELECT id FROM users WHERE user_name = 'obivan'),
(SELECT id FROM roles WHERE role_name = 'USER')
),
(
(SELECT id FROM users WHERE user_name = 'veider'),
(SELECT id FROM roles WHERE role_name = 'USER')
),
(
(SELECT id FROM users WHERE user_name = 'yoda'),
(SELECT id FROM roles WHERE role_name = 'ADMIN')
);

INSERT INTO products(product_name, price, fabricator_id)
VALUES('Netbook', 1000, (SELECT id FROM fabricators WHERE fabricator_name = 'Lenivec'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Phone', 200, (SELECT id FROM fabricators WHERE fabricator_name = 'Lenivec'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Gadjet', 10, (SELECT id FROM fabricators WHERE fabricator_name = 'Lenivec'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Headphones', 500, (SELECT id FROM fabricators WHERE fabricator_name = 'Sonic'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Player', 200, (SELECT id FROM fabricators WHERE fabricator_name = 'Sonic'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Videoplayer', 300, (SELECT id FROM fabricators WHERE fabricator_name = 'Sonic'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Pizza', 5, (SELECT id FROM fabricators WHERE fabricator_name = 'Bufet'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Bufroll', 3, (SELECT id FROM fabricators WHERE fabricator_name = 'Bufet'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Okroshka', 1, (SELECT id FROM fabricators WHERE fabricator_name = 'Bufet'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Candies', 3, (SELECT id FROM fabricators WHERE fabricator_name = 'Sladik'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Coockies', 2, (SELECT id FROM fabricators WHERE fabricator_name = 'Sladik'));
INSERT INTO products(product_name, price, fabricator_id)
VALUES('Cakes', 5, (SELECT id FROM fabricators WHERE fabricator_name = 'Sladik'));
