--liquibase formatted sql

--changeset umid:6

ALTER TABLE applications ADD COLUMN created_at DATE NOT NULL;