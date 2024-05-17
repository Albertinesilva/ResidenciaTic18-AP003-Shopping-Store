package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.Payment;
import br.com.techie.shoppingstore.AP003.model.User;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Cart cart;

    public Faker faker;

    private Cart createCart() {
        faker = new Faker();
        Cart cart = new Cart();
        cart.setId(faker.random().nextLong());
        cart.setUser(new User());
        cart.setItems(new HashSet<>());
        cart.setTotalItems(faker.random().nextInt(1, 100));
        cart.setTotalPrice(BigDecimal.valueOf(faker.random().nextDouble()));
        Date date = faker.date().between(Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(172800L)));
        cart.setPurchaseDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        cart.setStatus(PaymentStatusEnum.PENDING);
        cart.setPayment(new Payment());

        return cart;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        cart = createCart();
        entityManager.persistAndFlush(cart);
    }


    @Test
    public void testSave(){
        //Given
        Cart newCart = createCart();

        //When
        Cart savedCart = cartRepository.save(newCart);

        //Then
        assertThat(savedCart).isNotNull();
        assertThat(savedCart.getId()).isPositive();

    }

    @Test
    public void testFindById(){
        //  Given
        Cart savedCart = cartRepository.save(cart);

        //  When
        Optional<Cart> retrievedCart = cartRepository.findById(savedCart.getId());

        // Then
        assertTrue(retrievedCart.isPresent());
        assertEquals(cart, retrievedCart.get());

    }



}
