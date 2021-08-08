drop database if exists  splitbilldb;
create database splitbilldb;
use splitbilldb;

create table user(
user_id int AUTO_INCREMENT,
email_id varchar(50),
name varchar(50),
phone_number varchar(50),
password varchar(50),
constraint user_pk primary key (user_id)
);