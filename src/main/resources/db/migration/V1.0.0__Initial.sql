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
    tracking_number uuid default gen_random_uuid(),
    discount_id     bigint
        constraint fkap5cfg4japnftnsmgl3mboeq8
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

create table if not exists storedb.product_order
(
    id         bigserial
        primary key,
    product_id bigint not null
        constraint fkh73acsd9s5wp6l0e55td6jr1m
            references storedb.product,
    order_id   bigint
        constraint fk3jr9baphl9xkprqhtl94vn98e
            references storedb."order"
);

alter table storedb.product_order
    owner to postgres;

