INSERT INTO users(user_name, password, first_name, last_name)
VALUES('yoda', 'yodapassword', 'Ivan', 'Ivanov');
INSERT INTO users(user_name, password, first_name, last_name)
VALUES('obivan', 'obivanpassword', 'Obi', 'Van');
INSERT INTO users(user_name, password, first_name, last_name)
VALUES('veider', 'veiderpassword', 'Dart', 'Veider');

INSERT INTO roles(role_name) VALUES('user');
INSERT INTO roles(role_name) VALUES('admin');

INSERT INTO fabricators(fabricator_name) VALUES('Lenivec');
INSERT INTO fabricators(fabricator_name) VALUES('Sonic');
INSERT INTO fabricators(fabricator_name) VALUES('Bufet');
INSERT INTO fabricators(fabricator_name) VALUES('Sladik');

INSERT INTO users_roles VALUES
(
(SELECT id FROM users WHERE user_name = 'obivan'),
(SELECT id FROM roles WHERE role_name = 'user')
),
(
(SELECT id FROM users WHERE user_name = 'veider'),
(SELECT id FROM roles WHERE role_name = 'user')
),
(
(SELECT id FROM users WHERE user_name = 'yoda'),
(SELECT id FROM roles WHERE role_name = 'admin')
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
