create table product (
        price numeric(38,2),
        stock integer not null,
        category_id bigint,
        id bigserial not null,
        chassis varchar(255),
        chipset varchar(255),
        cpu varchar(255),
        description varchar(255),
        memory varchar(255),
        name varchar(255),
        network varchar(255),
        operational_system varchar(255),
        slots varchar(255),
        storage varchar(255),
        url_image varchar(255),
        primary key (id)
    )