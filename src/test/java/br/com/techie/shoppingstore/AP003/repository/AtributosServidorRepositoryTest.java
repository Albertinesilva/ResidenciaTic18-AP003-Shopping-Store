package br.com.techie.shoppingstore.AP003.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.AtributosServidor;

@DataJpaTest
public class AtributosServidorRepositoryTest {

    @Autowired
    private AtributosServidorRepository atributosServidorRepository;

    @Autowired
    private TestEntityManager entityManager;

    private AtributosServidor atributosServidor;

    public Faker faker;

    private AtributosServidor criaAtributosServidor() {
        faker = new Faker();
        AtributosServidor atributosServidor = new AtributosServidor();
        atributosServidor.setChassi(faker.lorem().word());
        atributosServidor.setProcessador(faker.lorem().word());
        atributosServidor.setSistema_operacional(faker.lorem().word());
        atributosServidor.setChipset(faker.lorem().word());
        atributosServidor.setMemoria(faker.lorem().word());
        atributosServidor.setSlots(faker.lorem().word());
        atributosServidor.setArmazenamento(faker.lorem().word());
        atributosServidor.setRede(faker.lorem().word());
        atributosServidor.setProduct(null); // You might want to adjust this based on your application logic
        return atributosServidor;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        atributosServidor = criaAtributosServidor();
        entityManager.persistAndFlush(atributosServidor);
    }

    @Test
    public void testSave() {
        // Given
        AtributosServidor newAtributosServidor = criaAtributosServidor();

        // When
        AtributosServidor savedAtributosServidor = atributosServidorRepository.save(newAtributosServidor);

        // Then
        assertThat(savedAtributosServidor).isNotNull();
        assertThat(savedAtributosServidor.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindById() {
        // Given
        AtributosServidor savedAtributosServidor = atributosServidorRepository.save(atributosServidor);

        // When
        Optional<AtributosServidor> retrievedAtributosServidor = atributosServidorRepository
                .findById(savedAtributosServidor.getId());

        // Then
        assertEquals(atributosServidor, retrievedAtributosServidor.get());
        assertTrue(retrievedAtributosServidor.isPresent());
    }

}