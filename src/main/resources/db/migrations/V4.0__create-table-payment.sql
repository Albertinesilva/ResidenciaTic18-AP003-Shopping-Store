create table payment (
        amount numeric(38,2),
        payment_type smallint check (payment_type between 0 and 3),
        id bigserial not null,
        payday timestamp(6),
        primary key (id)
    )