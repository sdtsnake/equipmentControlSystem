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
    order_number  int,
    idschool      int
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

CREATE view view_ordersbyschool AS
SELECT base.ordernro,
    base.orderdate,
    base.orderschool,
    base.orderschoolname,
    row_number() OVER (ORDER BY base.ordernro) AS idview
   FROM ( SELECT DISTINCT od.order_number AS ordernro,
            od.datecreate AS orderdate,
            sc.id AS orderschool,
            sc.name AS orderschoolname
           FROM orders od
             JOIN school sc ON od.idschool = sc.id) base;


alter table orders
    add constraint FK_orders_users_ing foreign key (idusercreate) references users (id);
alter table orders
    add constraint FK_orders_users_mod foreign key (idusermod) references users (id);
alter table orders
    add constraint FK_orders_school_id foreign key (idschool) references school (id);
