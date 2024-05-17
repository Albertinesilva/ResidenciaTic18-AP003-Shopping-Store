package br.com.techie.shoppingstore.AP003.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dt_pagamento;

    // TODO Remover referência a Carrinho
    @OneToOne(mappedBy = "pagamento")
    private Carrinho carrinho;

    // TODO Adicionar forma de pagamento como String
    private String forma_pagamento;

    // TODO Adicionar valor pago como BigDecimal
    private BigDecimal valor_pago;
}
