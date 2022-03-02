CREATE DATABASE Eb_Bill;
USE Eb_Bill;

CREATE TABLE Customer_info(

id int auto_increment primary key,
firstname varchar(30) NOT NULL,
lastname varchar(30)NOT NULL,
address varchar(30)NOT NULL,
UserID INT DEFAULT 9,
password varchar(30)NOT NULL,
type ENUM('H','C') NOT NULL
);

SHOW TABLES ;

SELECT * FROM Customer_info;
INSERT INTO Customer_info (firstname,lastname,address,password,type) values ('xxxxx','H','abc','@13124df','H');
