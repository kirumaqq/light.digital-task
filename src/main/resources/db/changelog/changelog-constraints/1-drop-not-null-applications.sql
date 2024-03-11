--liquibase formatted sql

--changeset umid:1
ALTER TABLE applications ALTER COLUMN phone_number DROP NOT NULL;


