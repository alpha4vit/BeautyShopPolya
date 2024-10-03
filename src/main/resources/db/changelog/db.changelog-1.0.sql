-- liquibase formatted sql

--changeset roman_gurinovich:1
create table if not exists users(
    id bigserial primary key,
    username varchar(255)
);

insert into users (username) values ('roman');