--liquibase formatted sql

--changeset umid:4

CREATE TABLE applications
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL ,
    text TEXT,
    phone_number VARCHAR(50) NOT NULL,
    user_id INT NOT NULL ,
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(id)
                ON UPDATE NO ACTION
                ON DELETE NO ACTION
);