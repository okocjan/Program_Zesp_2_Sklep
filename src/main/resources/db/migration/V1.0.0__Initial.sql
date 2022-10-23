create table if not exists storedb.customer
(
    id        bigserial
        primary key,
    address   varchar(200) not null,
    email     varchar(80)  not null,
    last_name varchar(50)  not null,
    name      varchar(50)  not null
);

create table if not exists storedb.discount_code
(
    id       bigserial
        primary key,
    code     varchar(30) not null,
    discount integer     not null
);

create table if not exists storedb."order"
(
    id          bigserial
        primary key,
    address     varchar(255) not null,
    order_date  date         not null,
    status      varchar(255),
    customer_id bigint       not null
        constraint fk1oduxyuuo3n2g98l3j7754vym
            references storedb.customer,
    discount_id bigint
        constraint fk7jd3v7xxhgpkgfxu4e8jojqlt
            references storedb.discount_code
);

create table if not exists storedb.order_send
(
    id               bigserial
        primary key,
    delivery_company integer          not null,
    total_price      double precision not null,
    tracking_number  integer          not null,
    order_id         bigint           not null
        constraint uk_n1isrg8n48kuluhfx5wq64eg1
            unique
        constraint fkmohbp9lkhlg23pqxxfrtxkew8
            references storedb."order"
            on delete cascade
);

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

create table if not exists storedb.product_picture
(
    product_id bigint        not null
        primary key
        constraint fkhna689todg1mb769hwfgcmsos
            references storedb.product
            on delete cascade,
    source     varchar(1024) not null
);

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

create table if not exists storedb.storage
(
    product_id bigint  not null
        primary key
        constraint fkpomua9n0dfb2v8h2wv063epie
            references storedb.product
            on delete cascade,
    count      integer not null
);
