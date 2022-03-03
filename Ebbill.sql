CREATE DATABASE EbBill;
USE EbBill;


CREATE TABLE Customer_info(

id int(50) not null auto_increment,
firstname varchar(30) NOT NULL,
lastname varchar(30)NOT NULL,
address varchar(30)NOT NULL,
UserID INT (10000) NOT NULL primary key,
password varchar(30)NOT NULL,
type ENUM('H','C') NOT NULL
);
alter table customer_info ADD UNIQUE INDEX(UserId);

SHOW TABLES ;

SELECT * FROM Customer_info;
INSERT IGNORE INTO Customer_info (firstname,lastname,address,UserID,password,type) values ('adjfh','H','abc','1','@13124df','H');
DELETE FROM Customer_info WHERE firstName='xxxxx';
