package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.Carrinho;
import br.com.techie.shoppingstore.AP003.model.Produto;

public record ItemCarrinhoDTO(int qtd, Long preco, Produto produto, Carrinho carrinho) {
}
