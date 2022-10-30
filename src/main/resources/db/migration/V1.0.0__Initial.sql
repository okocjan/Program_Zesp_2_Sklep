create table if not exists storedb.discount_code
(
    id       bigserial
        primary key,
    active   boolean     not null,
    code     varchar(30) not null
        constraint uk_pgevf2g3j2jbto0crethuhpyt
            unique,
    discount integer     not null
);

alter table storedb.discount_code
    owner to postgres;

create table if not exists storedb."order"
(
    id              bigserial
        primary key,
    address         varchar(150)     not null,
    delivery_type   varchar(10)      not null,
    email           varchar(150)     not null,
    last_name       varchar(150)     not null,
    name            varchar(150)     not null,
    order_date      date             not null,
    phone_number    varchar(12)      not null,
    status          varchar(15)      not null,
    total_price     double precision not null,
    tracking_number varchar(150)     not null,
    discount_id     bigint
        constraint fk7jd3v7xxhgpkgfxu4e8jojqlt
            references storedb.discount_code
);

alter table storedb."order"
    owner to postgres;

create table if not exists storedb.product
(
    id          bigserial
        primary key,
    capacity    integer          not null,
    description varchar(2048)    not null,
    name        varchar(255)     not null,
    price       double precision not null,
    type        varchar(255)     not null
);

alter table storedb.product
    owner to postgres;

create table if not exists storedb.product_picture
(
    product_id bigint        not null
        primary key
        constraint fkhna689todg1mb769hwfgcmsos
            references storedb.product,
    source     varchar(1024) not null
);

alter table storedb.product_picture
    owner to postgres;

create table if not exists storedb.ref_product_order
(
    id         serial
        primary key,
    order_id   bigint not null
        constraint fkbds4bkb025gvq65acw9l61jjx
            references storedb."order"
            on delete cascade,
    product_id bigint not null
        constraint fk3x3r6mfkne615sd63x3gxcrno
            references storedb.product
            on delete cascade
);

alter table storedb.ref_product_order
    owner to postgres;

create table if not exists storedb.storage
(
    product_id bigint  not null
        primary key
        constraint fkpomua9n0dfb2v8h2wv063epie
            references storedb.product,
    count      integer not null
);

alter table storedb.storage
    owner to postgres;

