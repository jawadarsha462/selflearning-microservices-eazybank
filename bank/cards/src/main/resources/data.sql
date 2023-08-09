Drop Table IF Exists customer;
drop table if exists cards;

create table customer(
    customer_id int auto_increment primary key,
    `name` varchar(100) not null,
    email varchar(100) not null,
    mobile_number varchar(20) not null,
    created_dt date default null
);

create table cards(
    customer_id int not null,
    card_id int auto_increment primary key,
    `card_number` varchar(100) not null,
    `card_type` varchar(100) not null,
    total_limit int not null,
    amount_used int not null,
    available_amount int not null,
    created_dt date default null
);

insert into customer(`name`,email, mobile_number,created_dt) values('Jawad','a@gmail.com','111111111',curdate());
insert into cards(`customer_id`,card_number,card_type, total_limit,amount_used,available_amount,created_dt) values(1,'111-111-111-111','CREDIT',100,50,50,curdate());