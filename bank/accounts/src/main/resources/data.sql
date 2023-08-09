Drop Table IF Exists customer;
drop table if exists accounts;

create table customer(
    customer_id int auto_increment primary key,
    `name` varchar(100) not null,
    email varchar(100) not null,
    mobile_number varchar(20) not null,
    created_dt date default null
);

create table accounts(
    customer_id int not null,
    account_number int auto_increment primary key,
    `account_type` varchar(100) not null,
    branch_address varchar(200) not null,
    created_dt date default null
);

insert into customer(`name`,email, mobile_number,created_dt) values('Jawad','a@gmail.com','111111111',curdate());
insert into accounts(`customer_id`,account_number, account_type,branch_address,created_dt) values(1,111111,'Saving','address',curdate());