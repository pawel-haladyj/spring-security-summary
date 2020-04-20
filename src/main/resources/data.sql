INSERT INTO user_table (id, name, username, password, email) VALUES
(1, 'Paweł', 'pawel', '12345', 'phaladyj@gmail.com'),
(2, 'Monika', 'monika', 'abcde', 'martikes@wp.pl');

INSERT INTO role_table(id,role) VALUES
(1, 'ADMIN_ROLE'),
(2, 'USER_ROLE');

INSERT INTO user_role (user_id,role_id) values
(1,1),
(1,2),
(2,2);