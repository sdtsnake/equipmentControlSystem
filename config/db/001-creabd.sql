create table orders
(
    id            serial primary key,
    model         varchar(500),
    serial_number varchar(500),
    asset         varchar(500),
    issue         varchar(500),
    incident      int,
    note          varchar(1000),
    status_order  char(50),
    idusercreate  int,
    datecreate    date,
    idusermod     int,
    order_number  int
);
create table school
(
    id   serial primary key,
    name varchar(500) not null
);
create table users
(
    id     serial primary key,
    email  varchar(250) not null,
    passwd varchar(500) not null,
    name   varchar(500) not null,
    rol    char(1)
);
create table orderbyschool
(
    idorder  int,
    idshcool int
);

alter table orders
    add constraint FK_orders_users_ing foreign key (idusercreate) references users (id);
alter table orders
    add constraint FK_orders_users_mod foreign key (idusermod) references users (id);
alter table orderbyschool
    add constraint FK_orders_school foreign key (idorder) references orders (id);
alter table orderbyschool
    add constraint FK_school_orders foreign key (idshcool) references school (id);