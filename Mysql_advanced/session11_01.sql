create database Hospital_DB;
use Hospital_DB;
    create table patients (
    id int primary key auto_increment,
    name varchar(100),
    age int,
    gender varchar(10)
);
insert into patients(name, age, gender) values
('Nguyen Van A', 25, 'Nam'),
('Tran Thi B', 30, 'Nu'),
('Le Van C', 40, 'Nam');