package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.ItemCarrinho;
import br.com.techie.shoppingstore.AP003.model.Pagamento;
import br.com.techie.shoppingstore.AP003.model.Usuario;

import java.time.LocalDateTime;
import java.util.Set;

public record CarrinhoDTO(Set<ItemCarrinho> item_carrinho_id, Pagamento pagamento, Usuario usuario, Long preco_total,
                          int qtd_itens,LocalDateTime dt_pedido,boolean status) {
}
