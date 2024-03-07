--liquibase formatted sql

--changeset umid:5

ALTER TABLE applications ADD COLUMN status VARCHAR(255) NOT NULL;