--liquibase formatted sql

--changeset umid:3

CREATE TABLE users_roles
(
    user_id INT,
    role_id INT,
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT roles_fk FOREIGN KEY (role_id) REFERENCES roles(id)
);