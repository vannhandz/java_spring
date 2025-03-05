create table clazz(
    id int auto_increment primary key ,
    name varchar(50)
);

create table student(
    id int auto_increment primary key ,
    name varchar(50),
    score double,
    clazz_id int,
    foreign key (clazz_id) references clazz(id)

);

