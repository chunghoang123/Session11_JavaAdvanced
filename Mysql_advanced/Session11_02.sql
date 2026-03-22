-- Chon database da tao
use Hospital_DB;

-- Tao bang Pharmacy cho bai tap Danh muc thuoc
create table pharmacy (
    id int primary key auto_increment,
    medicine_name varchar(255) not null,
    stock_quantity int default 0,
    price decimal(10, 2)
);

-- Chen du lieu mau de kiem tra vong lap while
insert into pharmacy(medicine_name, stock_quantity, price) values
('Paracetamol 500mg', 100, 2000.00),
('Amoxicillin 250mg', 50, 5500.00),
('Vitamin C 1000mg', 200, 3000.00),
('Panadol Extra', 0, 4500.00);