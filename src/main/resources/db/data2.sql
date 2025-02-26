create database student;

use student;

create table student
(
    id int auto_increment primary key,
    name char(55) not null,
    score double

);

insert into student(name ,score )
values ('nhan',10),
       ('tuan',10),
       ('tu',10);

select * from student;