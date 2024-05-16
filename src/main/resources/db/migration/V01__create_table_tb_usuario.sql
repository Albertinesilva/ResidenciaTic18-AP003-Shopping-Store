create table usuario (
  ativo boolean not null,
  id bigserial not null,
  confirmacao_senha varchar(255),
  email varchar(255),
  senha varchar(255),
  username varchar(255),
  primary key (id)
);