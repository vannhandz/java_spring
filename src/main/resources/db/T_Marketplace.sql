create database T_Marketplace;

use T_Marketplace;

create table loaimatbang(
    id_loaimatbang int auto_increment primary key ,
    name varchar(50)
);

create table matbang(
    id_matbang int auto_increment primary key ,
    name varchar(50),
    diachi varchar(50),
    dientich varchar(50),
    giathue double,
    ngaybatdauthue varchar(50),
    id_loaimatbang int,
    foreign key (id_loaimatbang) references loaimatbang(id_loaimatbang)

);

insert into loaimatbang(name)
values ('văn phòng'),
       ('cửa hàng'),
       ('nhà');

insert into matbang( name, diachi, dientich, giathue, ngaybatdauthue, id_loaimatbang)
values ('mặt bằng 1','đà nẵng','100m2',600000.0,'2025-02-26',1),
       ('mặt bằng 2','quảng nam','200m2',700000.0,'2025-01-10',2),
       ('mặt bằng 3','huế','300m2',800000.0,'2025-3-05',3);


select * from matbang;

select mb.id_matbang,mb.name,mb.diachi,mb.dientich,l.name,mb.giathue,mb.ngaybatdauthue from matbang mb join loaimatbang l on l.id_loaimatbang = mb.id_loaimatbang;

SELECT *
FROM matbang
WHERE
    id_matbang =?
  AND name LIKE '%' + ? + '%'
  AND diachi LIKE '%' + ? + '%'
  AND dientich BETWEEN ? AND ?
  AND id_loaimatbang = ?
  AND giathue BETWEEN ? AND ?
  AND ngaybatdauthue BETWEEN ? AND ?