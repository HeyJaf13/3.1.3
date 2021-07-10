CREATE TABLE users
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
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
CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    roles_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (roles_id) REFERENCES roles (id),

    UNIQUE (user_id, roles_id)
)
    ENGINE = InnoDB;

-- Insert data

INSERT INTO users
VALUES (1, 'Stanislav', '12345');
INSERT INTO users
VALUES (2, 'Tom', 'q123');
INSERT INTO users
VALUES (3, 'Jack', 'z123');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_roles
VALUES (1, 2);
INSERT INTO users_roles
VALUES (2, 1);
INSERT INTO users_roles
VALUES (3, 1);
INSERT INTO users_roles
VALUES (3, 2);

