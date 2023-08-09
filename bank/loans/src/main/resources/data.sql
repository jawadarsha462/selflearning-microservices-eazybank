Drop Table IF Exists customer;
drop table if exists loans;

create table customer(
    customer_id int auto_increment primary key,
    `name` varchar(100) not null,
    email varchar(100) not null,
    mobile_number varchar(20) not null,
    created_dt date default null
);

create table loans(
    customer_id int not null,
    loan_number int auto_increment primary key,
    `loan_type` varchar(100) not null,
    total_loan int not null,
    amount_paid int not null,
    outstanding_amount int not null,
    start_dt date default null,
    created_dt date default null
);

insert into customer(`name`,email, mobile_number,created_dt) values('Jawad','a@gmail.com','111111111',curdate());
insert into loans(`customer_id`,loan_type, total_loan,amount_paid,outstanding_amount,start_dt,created_dt) values(1,'loanType',100,50,50,curdate(),curdate());