INSERT INTO user_role(role, description)
VALUES ("ROLE_USER", "basic role - user"),
       ("ROLE_ADMIN", "admin role");

INSERT INTO user(id, activated, email, first_name, last_name, password)
VALUES (1, 1, "admin@game.pl", "Michael", "Burn", "{noop}adminpass"),
       (2, 1, "user@game.pl", "John", "Smith", "{noop}pass"),
       (3, 0, "user2@game.pl", "Ann", "Mariott", "{noop}pass");

INSERT INTO user_roles(user_id, roles_id)
VALUES (1, 2),
       (2, 1),
       (3, 1);

INSERT INTO category(id, name)
VALUES (1, "Apartment"),
       (2, "Comfort"),
       (3, "Standard");

INSERT INTO room(id, city, number, price, description, category_id, available)
VALUES (1, 'Warsaw', 101, 250, "Nice room with double bed. There is beautiful city view and terrace .", 2, 0),
       (2, 'Praha', 102, 500,
        "The luxurious room in five star hotel. It contains every facilities you will looking for.", 1, 1),
       (3, 'Rzeszow', 103, 100,
        "Standard accomodation. If you looking for a cozy room for small price, this offer is for you. ",
        3, 1);

INSERT INTO reservation(id, charge, end, start, room_id, user_id)
VALUES (1, 500, "2019-03-02", "2019-03-01", 1, 1);