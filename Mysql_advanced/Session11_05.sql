use hospital_db;

drop table if exists doctors;

create table doctors (
    doctor_id varchar(20) primary key,
    full_name varchar(100) not null,
    specialty varchar(50) not null
);

insert into doctors values
('D001', 'Bac si Nguyen Van A', 'Noi khoa'),
('D002', 'Bac si Tran Thi B', 'Nhi khoa');