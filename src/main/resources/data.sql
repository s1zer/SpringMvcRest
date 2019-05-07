INSERT INTO user_role(role, description)
VALUES ("ROLE_USER", "basic role - user"),
       ("ROLE_ADMIN", "admin role");

INSERT INTO user(id, activated, email, nickname, password)
VALUES (1, 1, "admin@game.pl", "adminek", "{noop}pass"),
       (2, 1, "user@game.pl", "userek", "{noop}pass"),
       (3, 0, "user2@game.pl", "userek22", "{noop}pass");

INSERT INTO user_roles(user_id, roles_id)
VALUES (1, 2),
       (2, 1),
       (3, 1);

INSERT INTO category(id, name)
VALUES (1, "Apartment"),
       (2, "Comfort"),
       (3, "Standard");

INSERT INTO room(id, number, price, description, category_id)
VALUES (1, 101,250, "Nice room with double bed. There is beautiful city view and terrace .", 2),
       (2, 102,500, "The luxurious room in five star hotel. It contains every facilities you will looking for.", 1),
       (3, 103,100, "Standard accomodation. If you looking for a cozy room for small price, this offer is for you. ", 3);