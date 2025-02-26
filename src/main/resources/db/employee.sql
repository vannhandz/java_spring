create database employees;

use employees;

create table department(
    id_department int auto_increment  primary key,
    name varchar(50)
);

create table employee(
    id_employee int auto_increment primary key ,
    name varchar(50),
    birth date,
    gender varchar(50),
    salary double,
    phone varchar(50),
    id_department int,
    foreign key (id_department) references department(id_department)
);

insert into department(name)
values ('chủ tịch'),
       ('giám đốc'),
       ('quản lý');

insert into employee(name, birth, gender, salary, phone, id_department)
values ('nhân','2003-10-03','nam',500000.0,'07876542',1),
       ('tuấn','2003-10-03','nam',600000.0,'07876542',2),
       ('tú','2003-10-03','nam',700000.0,'07876542',3);


select * from department;

drop database employees;