create database finmax;

use finmax;

create table login(
id int primary key,
username varchar(255),
password varchar (8)
);

create table account (
id int primary key,
f_name varchar(255) default("Abdullah"),
m_name varchar(255) default("Muhammed"),
l_name varchar(255) default("Abdullah"),
balance double,
foreign key(id) references login(id)
);

create table withdraw (
user_id int,
id int primary key AUTO_INCREMENT,
value double,
date_ date,
foreign key(user_id) references account(id)
);

create table deposit (
user_id int,
id    int primary key AUTO_INCREMENT ,
value double,
date_ date,
foreign key(user_id) references account(id)
);

select * from WD_group order by date_ limit 7