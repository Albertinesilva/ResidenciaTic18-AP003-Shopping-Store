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

import br.com.techie.shoppingstore.AP003.model.ItemCarrinho;


@DataJpaTest
public class ItemCarrinhoRepositoryTest {

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private TestEntityManager entityManager;

    private ItemCarrinho itemCarrinho;

    public Faker faker;

    private ItemCarrinho criaItemCarrinho() {

        faker = new Faker();
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setPreco(faker.number().randomNumber());
        itemCarrinho.setQtd(faker.number().randomDigit());
        itemCarrinho.setProduto(null);
        itemCarrinho.setCarrinho(null);
        
        return itemCarrinho;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        itemCarrinho = criaItemCarrinho();
        entityManager.persistAndFlush(itemCarrinho);
    }


    @Test
    public void testSave(){

        //Given
        ItemCarrinho newItemCarrinho = criaItemCarrinho();

        //When
        ItemCarrinho savedItemCarrinho = itemCarrinhoRepository.save(newItemCarrinho);

        //Then
        assertThat(savedItemCarrinho).isNotNull();
        assertThat(savedItemCarrinho.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindById(){
        //  Given
        ItemCarrinho savedItemCarrinho = itemCarrinhoRepository.save(itemCarrinho);

        //  When
        Optional<ItemCarrinho> retrievedItemCarrinho = itemCarrinhoRepository.findById(savedItemCarrinho.getId());

        // Then
        assertEquals(itemCarrinho, retrievedItemCarrinho.get());
        assertTrue(retrievedItemCarrinho.isPresent());

    }




}
