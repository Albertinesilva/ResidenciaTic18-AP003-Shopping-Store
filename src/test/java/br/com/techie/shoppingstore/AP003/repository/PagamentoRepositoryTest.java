package br.com.techie.shoppingstore.AP003.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.Carrinho;
import br.com.techie.shoppingstore.AP003.model.Pagamento;


@DataJpaTest
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Pagamento pagamento;

    private Carrinho carrinho;

    public Faker faker;

    private Pagamento criaPagamento() {

        faker = new Faker();
        carrinho = criaCarrinho();
        Pagamento pagamento = new Pagamento();
        pagamento.setDt_pagamento(null);
        pagamento.setCarrinho(carrinho); 
        return pagamento;
    }

    private Carrinho criaCarrinho() {

        faker = new Faker();
        Carrinho carrinho = new Carrinho();
        carrinho.setItem_carrinho_id(null);
        carrinho.setPagamento(null);
        carrinho.setUserSystem(null);
        carrinho.setPreco_total(faker.number().randomNumber());
        carrinho.setQtd_itens(faker.number().randomDigit());
        carrinho.setDt_pedido(null);
        carrinho.setStatus(faker.bool().bool());
        
        return carrinho;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        pagamento = criaPagamento();
        entityManager.persistAndFlush(carrinho);
        entityManager.persistAndFlush(pagamento);
    }


    @Test
    public void testSave(){

        //Given
        Pagamento newPagamento = criaPagamento();

        //When
        Pagamento savedPagamento = pagamentoRepository.save(newPagamento);

        //Then
        assertThat(savedPagamento).isNotNull();
        assertThat(savedPagamento.getId()).isGreaterThan(0);

        System.out.println(newPagamento);

    }

    @Test
    public void testFindById(){
        //  Given
        Pagamento savedPagamento = pagamentoRepository.save(pagamento);

        //  When
        Optional<Pagamento> retrievedPagamento = pagamentoRepository.findById(savedPagamento.getId());

        // Then
        assertEquals(pagamento, retrievedPagamento.get());
        assertTrue(retrievedPagamento.isPresent());

        System.out.println(retrievedPagamento);

    }




}

