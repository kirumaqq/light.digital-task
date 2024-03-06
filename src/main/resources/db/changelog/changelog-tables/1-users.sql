--liquibase formatted sql

--changeset umid:1

CREATE TABLE users
(
    id SERIAL PRIMARY KEY ,
    username VARCHAR(255) NOT NULL ,
    password_hash VARCHAR(255) NOT NULL ,
    CONSTRAINT unique_username UNIQUE (username)
);