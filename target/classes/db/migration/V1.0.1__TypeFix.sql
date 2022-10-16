ALTER TABLE storedb.product
    DROP COLUMN type;

ALTER TABLE storedb.product
    ADD type VARCHAR(1) NOT NULL;