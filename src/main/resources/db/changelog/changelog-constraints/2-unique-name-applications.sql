--liquibase formatted sql

--changeset umid:2
ALTER TABLE applications ADD CONSTRAINT unique_app UNIQUE (name);