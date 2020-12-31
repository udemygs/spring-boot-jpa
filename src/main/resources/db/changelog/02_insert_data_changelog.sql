--liquibase formatted sql

--changeset dev1:2
insert into Task
values(10001,'Spring Boot','Spring Boot Application', 'COMPLETED');

insert into Task
values(10002,'Spring Batch', 'Spring Batch Framework','PARTIAL_COMPLETED');

--rollback delete from Task where id in (10001,10002)