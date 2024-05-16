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