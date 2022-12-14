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

CREATE TABLE IF NOT EXISTS public.diary
(
    id integer NOT NULL DEFAULT nextval('diary_id_seq'::regclass),
    iduser integer NOT NULL,
    idschool integer,
    start_time timestamp without time zone,
    ending_time timestamp without time zone,
    replacement "char",
    weekday integer,
    CONSTRAINT diary_pkey PRIMARY KEY (iduser),
    CONSTRAINT fk_diary_school_id FOREIGN KEY (id)
        REFERENCES public.school (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_diary_users_id FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)




alter table orders
    add constraint FK_orders_users_ing foreign key (idusercreate) references users (id);
alter table orders
    add constraint FK_orders_users_mod foreign key (idusermod) references users (id);
alter table orders
    add constraint FK_orders_school_id foreign key (idschool) references school (id);

CREATE INDEX IF NOT EXISTS fki_fk_diary_school_id
    ON public.diary USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX IF NOT EXISTS fki_fk_diary_users_id
    ON public.diary USING btree
    (id ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX IF NOT EXISTS fki_fk_orders_user_id
    ON public.diary USING btree
    (iduser ASC NULLS LAST)
    TABLESPACE pg_default;