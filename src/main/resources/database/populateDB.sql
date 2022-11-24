INSERT INTO users(name, password, first_name, last_name) 
VALUES('yoda', 'yodapassword', 'Ivan', 'Ivanov');
INSERT INTO users(name, password, first_name, last_name) 
VALUES('obivan', 'obivanpassword', 'Obi', 'Van');
INSERT INTO users(name, password, first_name, last_name) 
VALUES('veider', 'veiderpassword', 'Dart', 'Veider');

INSERT INTO roles(name) VALUES('user');
INSERT INTO roles(name) VALUES('admin');

INSERT INTO fabricators(name) VALUES('Lenivec');
INSERT INTO fabricators(name) VALUES('Sonic');
INSERT INTO fabricators(name) VALUES('Bufet');
INSERT INTO fabricators(name) VALUES('Sladik');

INSERT INTO products(name, price, fabricator_name) 
VALUES('Netbook', 1000, 'Lenivec');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Phone', 200, 'Lenivec');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Gadjet', 10, 'Lenivec');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Headphones', 500, 'Sonic');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Player', 200, 'Sonic');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Videoplayer', 300, 'Sonic');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Pizza', 5, 'Bufet');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Bufroll', 3, 'Bufet');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Okroshka', 1, 'Bufet');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Candies', 3, 'Sladik');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Coockies', 2, 'Sladik');
INSERT INTO products(name, price, fabricator_name) 
VALUES('Cakes', 5, 'Sladik');
