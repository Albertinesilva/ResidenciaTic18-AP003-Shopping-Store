create table item_carrinho (
  qtd integer not null,
  id bigserial not null,
  item_carrinho_id bigint,
  preco bigint,
  produto_id bigint unique,
  primary key (id)
);