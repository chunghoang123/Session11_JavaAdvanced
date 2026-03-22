use Hospital_DB;

create table beds (
    bed_id varchar(20) primary key,
    room_number varchar(10),
    bed_status varchar(50) default 'Trong'
);

insert into beds(bed_id, room_number, bed_status) values
('Bed_001', 'Room_101', 'Trong'),
('Bed_002', 'Room_101', 'Trong'),
('Bed_003', 'Room_102', 'Trong');