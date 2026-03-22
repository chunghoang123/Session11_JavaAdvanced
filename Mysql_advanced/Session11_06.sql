create database medicalappointmentdb;

use medicalappointmentdb;

create table appointments (
    id int primary key auto_increment,
    patient_name varchar(255) not null,
    appointment_date date not null,
    doctor_name varchar(255) not null,
    status varchar(50) default 'Cho kham'
);

-- du lieu mau
insert into appointments (patient_name, appointment_date, doctor_name, status)
values ('Nguyen Van A', '2026-03-25', 'Bac si Hung', 'Cho kham');