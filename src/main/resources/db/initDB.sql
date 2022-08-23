DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                           NOT NULL,
    email      VARCHAR                           NOT NULL,
    password   VARCHAR                           NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    enabled    BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE restaurants
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR NOT NULL,
    address VARCHAR NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE votes
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id       INTEGER                                  NOT NULL,
    restaurant_id INTEGER                                  NOT NULL,
    local_date    TIMESTAMP           DEFAULT current_date NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_id_local_date_idx ON votes (user_id, local_date);



CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE meals
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER                                  NOT NULL,
    name          VARCHAR                                  NOT NULL,
    price         DECIMAL                                  NOT NULL,
    local_date    timestamp           DEFAULT current_date NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX meals_unique_restaurant_name_date_idx on meals (restaurant_id, name, local_date);