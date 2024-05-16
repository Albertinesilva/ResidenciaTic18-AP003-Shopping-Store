package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.Produto;

import java.util.Set;

public record CategoriaDTO(String nome, Set<Produto> produtos) {
}
