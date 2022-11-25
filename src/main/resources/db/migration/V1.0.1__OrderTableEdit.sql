alter table storedb."order"
    alter column tracking_number type uuid using tracking_number::uuid;

alter table storedb."order"
    alter column tracking_number set default gen_random_uuid();

alter table storedb."order"
    add constraint order_pk
        unique (tracking_number);

alter table storedb."order"
    alter column tracking_number drop not null;