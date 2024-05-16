alter table if exists item_carrinho 
    add constraint FKd8mjcc8xs0o1teytpk1fo0rjj 
    foreign key (item_carrinho_id) 
    references carrinho;

alter table if exists item_carrinho 
    add constraint FK7he6x1mtdwm4fmlsa09yxjifx 
    foreign key (produto_id) 
    references produto;