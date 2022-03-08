CREATE DATABASE EbBill;
USE EbBill;

SHOW TABLES ;

SELECT * FROM Customer;

CREATE TABLE Customer(
id int(255) not null auto_increment,
firstname varchar(30) NOT NULL,
lastname varchar(30)NOT NULL,
address varchar(30)NOT NULL,
UserID INT (255) NOT NULL,
type ENUM('H','C') NOT NULL,
previousMonthReading int(255),
CurrentMonthReading int(255),
status varchar(30) not null,
Primary key(id,UserID)
);

ALTER TABLE Customer
MODIFY COLUMN status varchar(30) default 'Unpaid';
alter table Customer add totalAmount int(255) after CurrentMonthReading;
