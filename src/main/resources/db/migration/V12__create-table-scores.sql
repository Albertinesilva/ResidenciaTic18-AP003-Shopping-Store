create table scores (
    id bigserial not null,
    score integer not null,
    user_comment varchar(255),
    user_id bigint not null,
    product_id bigint,

    primary key (id),
    constraint fk_user_score foreign key (user_id) references users(id),
    constraint fk_product_score foreign key (product_id) references product(id)
);