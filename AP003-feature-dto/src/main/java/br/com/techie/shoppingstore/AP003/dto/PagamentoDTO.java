package br.com.techie.shoppingstore.AP003.dto;

import br.com.techie.shoppingstore.AP003.model.Carrinho;

import java.time.LocalDateTime;

public record PagamentoDTO(LocalDateTime dt_pagamento, Carrinho carrinho) {
}
