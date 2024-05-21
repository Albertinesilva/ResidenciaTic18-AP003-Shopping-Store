create table cart (
        status smallint check (status between 0 and 2),
        total_items integer not null,
        total_price numeric(38,2),
        id bigserial not null,
        payment_id bigint unique,
        purchase_date timestamp(6),
        user_id bigint unique,
        primary key (id)
    )