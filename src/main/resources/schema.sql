-- Users and Authorities
drop table if exists users;
drop table if exists authorities;
drop index if exists ix_auth_username;

create table if not exists users (
    username varchar2(50) not null primary key,
    password varchar2(50) not null,
    enabled char(1) default '1'
);

create table if not exists authorities (
    username varchar2(50) not null,
    authority varchar2(50) not null,
    constraint fk_authorities_users
        foreign key(username) references users(username)
);

create unique index ix_auth_username
    on authorities (username, authority);

drop table if exists ingredient;
drop table if exists taco;
drop table if exists taco_ingredients;

-- Ingredients
create table if not exists ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

-- Tacos
create table if not exists taco (
    id identity,
    name varchar(50) not null,
    created_at timestamp not null
);

create table if not exists taco_ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table taco_ingredients
    add foreign key (taco) references taco(id);
alter table taco_ingredients
    add foreign key (ingredient) references ingredient(id);

-- Orders

drop table if exists taco_order;
drop table if exists taco_order_tacos;


create table if not exists taco_order (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists taco_order_tacos (
    taco_order bigint not null,
    taco bigint not null
);

alter table taco_order_tacos
    add foreign key (taco_order) references taco_order(id);
alter table taco_order_tacos
    add foreign key (taco) references taco(id);
