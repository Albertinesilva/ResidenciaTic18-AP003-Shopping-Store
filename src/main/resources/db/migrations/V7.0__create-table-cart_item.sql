 create table cart_item (
        price numeric(38,2),
        quantity integer not null,
        cart_id bigint,
        id bigserial not null,
        product_id bigint,
        primary key (id)
    )