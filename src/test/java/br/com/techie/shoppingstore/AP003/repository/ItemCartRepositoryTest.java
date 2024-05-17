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

import br.com.techie.shoppingstore.AP003.model.CartItem;


@DataJpaTest
public class ItemCartRepositoryTest {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private CartItem cartItem;

    public Faker faker;

    private CartItem criaItemCarrinho() {

        faker = new Faker();
        CartItem cartItem = new CartItem();
        cartItem.setPreco(faker.number().randomNumber());
        cartItem.setQtd(faker.number().randomDigit());
        cartItem.setProduct(null);
        cartItem.setCart(null);
        
        return cartItem;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        cartItem = criaItemCarrinho();
        entityManager.persistAndFlush(cartItem);
    }


    @Test
    public void testSave(){

        //Given
        CartItem newCartItem = criaItemCarrinho();

        //When
        CartItem savedCartItem = cartItemRepository.save(newCartItem);

        //Then
        assertThat(savedCartItem).isNotNull();
        assertThat(savedCartItem.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindById(){
        //  Given
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        //  When
        Optional<CartItem> retrievedItemCarrinho = cartItemRepository.findById(savedCartItem.getId());

        // Then
        assertEquals(cartItem, retrievedItemCarrinho.get());
        assertTrue(retrievedItemCarrinho.isPresent());

    }




}
