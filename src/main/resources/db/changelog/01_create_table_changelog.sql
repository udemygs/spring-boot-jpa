--liquibase formatted sql

--changeset dev1:1
create table Task(
id int primary key,
title varchar(50),
description varchar(50),
status varchar(50)
);

--rollback drop table Task