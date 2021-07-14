CREATE TABLE user
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname     VARCHAR(255) NOT NULL,
    lastname    VARCHAR(255) NOT NULL,
    age     VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(100) NOT NULL
)
    ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles
(
    user_id INT NOT NULL,
    roles_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (roles_id) REFERENCES roles (id),

    UNIQUE (user_id, roles_id)
)
    ENGINE = InnoDB;

-- Insert data

INSERT INTO user
VALUES (1, 'Stanislav', 'Dusiak', 31, 'stas@dusiakgmail.com', '12345');
INSERT INTO user
VALUES (2, 'Tom', 'Hardy', 45, 'TomH@mail.ru', 'q123');
INSERT INTO user
VALUES (3, 'Jack', 'Richer', 51, 'Jackgmail.com', 'z123');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 2);
INSERT INTO user_roles
VALUES (2, 1);
INSERT INTO user_roles
VALUES (3, 1);
INSERT INTO user_roles
VALUES (3, 2);

