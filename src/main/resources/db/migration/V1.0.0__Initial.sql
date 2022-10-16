create table if not exists product
(
    id       serial
        constraint table_name_pk
            primary key,
    name     varchar not null,
    capacity integer not null,
    price    real    not null,
    type     char    not null
);

comment on column product.type is 'one letter';

alter table product
    owner to postgres;

create table if not exists product_picture
(
    product_id integer not null
        constraint product_picture_pk
            unique
        constraint prod_img_fk
            references product,
    source     varchar not null,
    id         serial
        constraint product_picture_id_pk
            primary key
);

alter table product_picture
    owner to postgres;

create table if not exists customer
(
    id        serial
        constraint customer_pk
            primary key,
    name      varchar(50)  not null,
    last_name varchar(50)  not null,
    address   varchar(200) not null,
    email     varchar(80)  not null
        constraint email_unique
            unique
);

alter table customer
    owner to postgres;

create table if not exists discount_code
(
    id       serial
        constraint discount_code_pk
            primary key,
    code     varchar(30) not null
        constraint discount_code_unique
            unique,
    discount integer     not null
);

alter table discount_code
    owner to postgres;

create table if not exists "order"
(
    id          serial
        constraint order_pk
            primary key,
    order_date  date    not null,
    total_price real    not null,
    customer_id integer not null
        constraint order_customer_id_fk
            references customer
            on update cascade on delete cascade,
    discount_id integer
        constraint order_discount_code_null_fk
            references discount_code
);

alter table "order"
    owner to postgres;

create table if not exists ref_product_order
(
    product_id integer not null
        constraint ref_product_order_product_null_fk
            references product,
    order_id   integer not null
        constraint ref_product_order_order_null_fk
            references "order",
    id         integer generated always as identity
        constraint ref_product_order_pk
            primary key
);

alter table ref_product_order
    owner to postgres;

create table if not exists "user"
(
    id          serial
        constraint user_pk
            primary key,
    username    varchar(100) not null,
    password    varchar(100) not null,
    customer_id integer      not null
        constraint user_customer_null_fk
            references customer
);

alter table "user"
    owner to postgres;

create table if not exists order_send
(
    order_id         integer not null
        constraint order_send_order_null_fk
            references "order",
    tracking_number  integer not null,
    delivery_company integer not null,
    id               serial
        constraint order_send_pk
            primary key
);

alter table order_send
    owner to postgres;

create unique index if not exists order_send_order_id_uindex
    on order_send (order_id);

create table if not exists storage
(
    product_id integer not null
        constraint storage_product_null_fk
            references product
            on update cascade on delete cascade,
    count      integer not null,
    id         serial
        constraint storage_pk
            primary key
);

alter table storage
    owner to postgres;

