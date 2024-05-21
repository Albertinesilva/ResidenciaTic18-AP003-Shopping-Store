create table users (
        active boolean not null,
        date_creation timestamp(6),
        date_modification timestamp(6),
        id bigserial not null,
        role varchar(25) not null check (role in ('ROLE_ADMIN','ROLE_CLIENT')),
        code_verifier varchar(200),
        creator_by varchar(255),
        email varchar(255) not null unique,
        modified_by varchar(255),
        password varchar(255),
        password_confirm varchar(255),
        username varchar(255),
        primary key (id)
    )