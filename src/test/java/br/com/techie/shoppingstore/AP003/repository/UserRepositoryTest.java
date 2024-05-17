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

import br.com.techie.shoppingstore.AP003.model.User;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    public Faker faker;

    private User criaUsuario() {
        
        faker = new Faker();
        User user = new User();
        user.setActive(faker.bool().bool());
        user.setPassword("@Test123");
        user.setPasswordConfirm("@Test123");
        user.setEmail(faker.internet().emailAddress());
        user.setUsername(faker.name().username());
        return user;
    }



    @BeforeEach
    public void setUp() {
        faker = new Faker();
        user = criaUsuario();
        entityManager.persistAndFlush(user);
    }


    @Test
    public void testSave(){

        //Given
        User newUser = criaUsuario();

        //When
        User savedUser = userRepository.save(newUser);

        //Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);

        System.out.println(newUser);

    }

    @Test
    public void testFindById(){
        //  Given
        User savedUser = userRepository.save(user);

        //  When
        Optional<User> retrievedUsuario = userRepository.findById(savedUser.getId());

        // Then
        assertEquals(user, retrievedUsuario.get());
        assertTrue(retrievedUsuario.isPresent());

        System.out.println(retrievedUsuario);

    }


}
