create table if not exists storedb.product
(
    id       serial
        constraint table_name_pk
            primary key,
    name     varchar  not null,
    capacity integer  not null,
    price    real     not null,
    type     smallint not null
);

alter table storedb.product
    owner to postgres;

create table if not exists storedb.product_picture
(
    product_id integer not null
        constraint product_picture_pk
            unique
        constraint uk_11i5d1m4v5p54k12cvla6ckei
            unique
        constraint prod_img_fk
            references storedb.product,
    source     varchar not null,
    id         serial
        constraint product_picture_id_pk
            primary key
);

alter table storedb.product_picture
    owner to postgres;

create table if not exists storedb.customer
(
    id        serial
        constraint customer_pk
            primary key,
    name      varchar(50)  not null,
    last_name varchar(50)  not null,
    address   varchar(200) not null,
    email     varchar(80)  not null
        constraint email_unique
            unique,
    user_id   integer      not null
);

alter table storedb.customer
    owner to postgres;

create table if not exists storedb.discount_code
(
    id       serial
        constraint discount_code_pk
            primary key,
    code     varchar(30) not null
        constraint discount_code_unique
            unique,
    discount integer     not null
);

alter table storedb.discount_code
    owner to postgres;

create table if not exists storedb."order"
(
    id          serial
        constraint order_pk
            primary key,
    order_date  date    not null,
    total_price real    not null,
    customer_id integer not null
        constraint order_customer_id_fk
            references storedb.customer
            on update cascade on delete cascade,
    discount_id integer
        constraint order_discount_code_null_fk
            references storedb.discount_code
);

alter table storedb."order"
    owner to postgres;

create table if not exists storedb.ref_product_order
(
    product_id integer not null
        constraint ref_product_order_product_null_fk
            references storedb.product,
    order_id   integer not null
        constraint ref_product_order_order_null_fk
            references storedb."order",
    id         integer generated always as identity
        constraint ref_product_order_pk
            primary key
);

alter table storedb.ref_product_order
    owner to postgres;

create table if not exists storedb.order_send
(
    order_id         integer not null
        constraint uk_n1isrg8n48kuluhfx5wq64eg1
            unique
        constraint order_send_order_null_fk
            references storedb."order",
    tracking_number  integer not null,
    delivery_company integer not null,
    id               serial
        constraint order_send_pk
            primary key
);

alter table storedb.order_send
    owner to postgres;

create unique index if not exists order_send_order_id_uindex
    on storedb.order_send (order_id);

create table if not exists storedb.storage
(
    product_id integer not null
        constraint storage_product_null_fk
            references storedb.product
            on update cascade on delete cascade,
    count      integer not null,
    id         serial
        constraint storage_pk
            primary key
);

alter table storedb.storage
    owner to postgres;

