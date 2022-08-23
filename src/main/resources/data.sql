INSERT INTO users (ID, NAME, EMAIL, REGISTERED, ENABLED, PASSWORD)
VALUES (1, 'User', 'user@yandex.ru', now(), true, '$2a$10$LsiVBBis4SvWCCW83MIu5uXjLo4Nv5ynyrFzx5strRb0vKC2zFS5W'),
       (2, 'Admin', 'admin@gmail.com', now(), true, '$2a$10$LsiVBBis4SvWCCW83MIu5uXjLo4Nv5ynyrFzx5strRb0vKC2zFS5W'),
       (3, 'Guest', 'guest@gmail.com', now(), true, '$2a$10$LsiVBBis4SvWCCW83MIu5uXjLo4Nv5ynyrFzx5strRb0vKC2zFS5W');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurants (ID, name, address)
VALUES (4, 'Клод Монэ', 'Москва, Пушкинская площадь, д.2'),
       (5, 'Аркобалено', 'Москва, Пушкинская площадь, д.3'),
       (6, 'Дом Нино', 'Самара, Московское шоссе, д.145'),
       (7, 'Чипчили', 'Самара, Хлебная площадь, д.14'),
       (8, 'КФС', 'Самара, Московское шоссе, д.1');


INSERT INTO meals (ID, restaurant_id, name, price, local_date)
VALUES (9, 4, 'Soup', 100.00, current_date),
       (10, 4, 'Snack', 50.00, current_date),
       (11, 4, 'Tea', 20.00, current_date),
       (12, 5, 'Soup', 100.00, current_date),
       (13, 5, 'Snack', 50.00, current_date),
       (14, 5, 'Tea', 20.00, current_date),
       (15, 6, 'Soup', 100.00, current_date),
       (16, 6, 'Snack', 600.00, current_date),
       (17, 6, 'Coffee', 30.00, current_date),
       (18, 7, 'Coffee', 30.00, current_date),
       (19, 7, 'Tea', 30.00, current_date),
       (20, 7, 'Sugar', 30.00, current_date),
       (21, 8, 'Soup', 30.00, current_date),
       (22, 8, 'Eggs', 30.00, current_date),
       (23, 8, 'Grill', 30.00, current_date);



INSERT INTO votes (ID, user_id, restaurant_id, local_date)
VALUES (24, 1, 4, current_date),
       (25, 2, 5, current_date),
       (26, 3, 6, current_date);

