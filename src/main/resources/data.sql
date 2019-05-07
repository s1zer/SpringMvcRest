INSERT INTO user_role(role, description)
VALUES ("ROLE_USER", "basic role - user"),
       ("ROLE_ADMIN","admin role");

INSERT INTO user(id, activated, email, nickname, password)
VALUES (1, 1, "admin@game.pl", "adminek", "{noop}pass"),
       (2, 1, "user@game.pl", "userek", "{noop}pass"),
       (3, 0, "user2@game.pl", "userek22", "{noop}pass");

INSERT INTO user_roles(user_id, roles_id)
VALUES (1, 2),
       (2, 1),
       (3,1);