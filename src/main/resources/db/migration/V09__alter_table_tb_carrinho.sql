alter table if exists carrinho 
    add constraint FKjvuvwj5bk3gmk3ixutiinidv 
    foreign key (pagamento_id) 
    references pagamento;

alter table if exists carrinho 
    add constraint FK8jwo8e9vk1gdcw8ak7if31ahc 
    foreign key (usuario_id) 
    references usuario;