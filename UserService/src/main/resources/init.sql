create table users (
id bigint auto_increment primary key,
name varchar(50),
balance int
);

create table user_transaction (
id bigint auto_increment primary key,
user_id bigint,
amount int,
transaction_date timestamp,
foreign key (user_id) references users(id)
);