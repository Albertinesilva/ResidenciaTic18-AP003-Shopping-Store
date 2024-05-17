package br.com.techie.shoppingstore.AP003.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.Cart;
import br.com.techie.shoppingstore.AP003.model.Payment;


@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Payment payment;

    private Cart cart;

    public Faker faker;

    private Payment criaPagamento() {

        faker = new Faker();
        cart = criaCarrinho();
        Payment payment = new Payment();
        payment.setPaymentType(PaymentTypeEnum.MONEY);
        return payment;
    }

    private Cart criaCarrinho() {

        faker = new Faker();
        Cart cart = new Cart();
        cart.setPayment(null);
        cart.setUser(null);
        cart.setTotalItems(faker.random().nextInt(1, 100));
        cart.setTotalPrice(BigDecimal.valueOf(faker.random().nextDouble()));
        cart.setPurchaseDate(null);
        cart.setStatus(PaymentStatusEnum.PENDING);
        
        return cart;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        payment = criaPagamento();
        entityManager.persistAndFlush(cart);
        entityManager.persistAndFlush(payment);
    }


    @Test
    public void testSave(){

        //Given
        Payment newPayment = criaPagamento();

        //When
        Payment savedPayment = paymentRepository.save(newPayment);

        //Then
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isGreaterThan(0);

        System.out.println(newPayment);

    }

    @Test
    public void testFindById(){
        //  Given
        Payment savedPayment = paymentRepository.save(payment);

        //  When
        Optional<Payment> retrievedPagamento = paymentRepository.findById(savedPayment.getId());

        // Then
        assertEquals(payment, retrievedPagamento.get());
        assertTrue(retrievedPagamento.isPresent());

        System.out.println(retrievedPagamento);

    }




}

