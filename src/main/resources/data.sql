INSERT INTO users (id, name, username, password, email) VALUES
(1, 'Paweł', 'pawel', '12345', 'phaladyj@gmail.com'),
(2, 'Monika', 'monika', 'abcde', 'martikes@wp.pl');

INSERT INTO roles(role) VALUES
('ADMIN_ROLE'),
('USER_ROLE');

INSERT INTO user_roles (user_id,role_id) values
(1,'ADMIN_ROLE'),
(1,'USER_ROLE'),
(2,'USER_ROLE');