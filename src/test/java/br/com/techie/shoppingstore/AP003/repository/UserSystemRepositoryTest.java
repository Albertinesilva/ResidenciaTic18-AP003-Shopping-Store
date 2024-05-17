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

import br.com.techie.shoppingstore.AP003.model.UserSystem;


@DataJpaTest
public class UserSystemRepositoryTest {

    @Autowired
    private UserSystemRepository userSystemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private UserSystem userSystem;

    public Faker faker;

    private UserSystem criaUserSystem() {
        
        faker = new Faker();
        UserSystem userSystem = new UserSystem();
        userSystem.setActive(faker.bool().bool());
        userSystem.setPassword("@Test123");
        userSystem.setEmail(faker.internet().emailAddress());
        userSystem.setUsername(faker.name().username());
        return userSystem;
    }



    @BeforeEach
    public void setUp() {
        faker = new Faker();
        userSystem = criaUserSystem();
        entityManager.persistAndFlush(userSystem);
    }


    @Test
    public void testSave(){

        //Given
        UserSystem newUserSystem = criaUserSystem();

        //When
        UserSystem savedUserSystem = userSystemRepository.save(newUserSystem);

        //Then
        assertThat(savedUserSystem).isNotNull();
        assertThat(savedUserSystem.getId()).isGreaterThan(0);

        System.out.println(newUserSystem);

    }

    @Test
    public void testFindById(){
        //  Given
        UserSystem savedUserSystem = userSystemRepository.save(userSystem);

        //  When
        Optional<UserSystem> retrievedUserSystem = userSystemRepository.findById(savedUserSystem.getId());

        // Then
        assertEquals(userSystem, retrievedUserSystem.get());
        assertTrue(retrievedUserSystem.isPresent());

        System.out.println(retrievedUserSystem);

    }


}
