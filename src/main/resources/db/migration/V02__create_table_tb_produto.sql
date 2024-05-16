create table produto (
  qtd_estoque integer not null,
  categoria_id bigint,
  id bigserial not null,
  preco bigint,
  descricao varchar(255),
  nome varchar(255),
  url_imagem varchar(255),
  primary key (id)
);