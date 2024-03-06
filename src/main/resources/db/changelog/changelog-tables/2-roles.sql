--liquibase formatted sql

--changeset umid:2

CREATE TABLE roles
(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL ,
    CONSTRAINT unique_name UNIQUE (name)
);