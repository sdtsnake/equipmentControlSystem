create table orders(id serial primary key, model varchar(500), nro_serial varchar(500),asset varchar(500),issue varchar(500),insident int, note varchar(1000), status_order char(50), idusercreate int, datacreate date, nro_order int);
create table shcool(id serial primary key, descripcion varchar(500) not null);
create table user(id serial primary key,  email varchar(250) not null, passwd varchar(500) not null, descripcion varchar(500) not null, rol char(1));
create table orderbyshcool(idorder int, idshcool int);

