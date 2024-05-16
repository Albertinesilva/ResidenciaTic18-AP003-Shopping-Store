create table carrinho (
  qtd_itens integer not null,
  status boolean not null,
  dt_pedido timestamp(6),
  id bigserial not null,
  pagamento_id bigint unique,
  preco_total bigint,
  usuario_id bigint unique,
  primary key (id)
);