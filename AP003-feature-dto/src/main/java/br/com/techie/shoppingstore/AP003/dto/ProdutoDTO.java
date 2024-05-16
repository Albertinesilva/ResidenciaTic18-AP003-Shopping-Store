package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.AtributosServidor;
import br.com.techie.shoppingstore.AP003.model.Categoria;
import br.com.techie.shoppingstore.AP003.model.ItemCarrinho;

public record ProdutoDTO(String nome, Long preco, String descricao, int qtd_estoque, String url_imagem,
                         AtributosServidor atributosServidor, ItemCarrinho itemCarrinho, Categoria categoria) {
}
