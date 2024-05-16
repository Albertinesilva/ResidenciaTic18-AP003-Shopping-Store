package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.Carrinho;

public record UsuarioDTO(String email, String username, String senha, String confirmacao_senha, boolean ativo,
                         Carrinho carrinho) {
}
