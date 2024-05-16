
  create table atributos_servidor (
    id bigserial not null,
    produto_id bigint unique,
    armazenamento varchar(255),
    chassi varchar(255),
    chipset varchar(255),
    memoria varchar(255),
    processador varchar(255),
    rede varchar(255),
    sistema_operacional varchar(255),
    slots varchar(255),
    primary key (id)
  );

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

  create table categoria (
    id bigserial not null,
    nome varchar(255),
    primary key (id)
  );

  create table item_carrinho (
    qtd integer not null,
    id bigserial not null,
    item_carrinho_id bigint,
    preco bigint,
    produto_id bigint unique,
    primary key (id)
  );

  create table pagamento (
    dt_pagamento timestamp(6),
    id bigserial not null,
    primary key (id)
  );

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

  create table usuario (
    ativo boolean not null,
    id bigserial not null,
    confirmacao_senha varchar(255),
    email varchar(255),
    senha varchar(255),
    username varchar(255),
    primary key (id)
  );

  alter table if exists atributos_servidor 
    add constraint FK442ylrxjcpl3exwjbwhm9sgb3 
    foreign key (produto_id) 
    references produto;

  alter table if exists carrinho 
    add constraint FKjvuvwj5bk3gmk3ixutiinidv 
    foreign key (pagamento_id) 
    references pagamento;

  alter table if exists carrinho 
    add constraint FK8jwo8e9vk1gdcw8ak7if31ahc 
    foreign key (usuario_id) 
    references usuario;

  alter table if exists item_carrinho 
    add constraint FKd8mjcc8xs0o1teytpk1fo0rjj 
    foreign key (item_carrinho_id) 
    references carrinho;

  alter table if exists item_carrinho 
    add constraint FK7he6x1mtdwm4fmlsa09yxjifx 
    foreign key (produto_id) 
    references produto;

  alter table if exists produto 
    add constraint FKopu9jggwnamfv0c8k2ri3kx0a 
    foreign key (categoria_id) 
    references categoria;
