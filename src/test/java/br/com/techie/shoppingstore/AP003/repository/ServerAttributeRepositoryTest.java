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

import br.com.techie.shoppingstore.AP003.model.ServerAttribute;

@DataJpaTest
public class ServerAttributeRepositoryTest {

    @Autowired
    private ServerAttributeRepository serverAttributeRepository;
    
    @Autowired
    private TestEntityManager entityManager;

    private ServerAttribute serverAttribute;

    public Faker faker;

    private ServerAttribute criaAtributosServidor() {
        faker = new Faker();
        ServerAttribute serverAttribute = new ServerAttribute();
        serverAttribute.setChassi(faker.lorem().word());
        serverAttribute.setProcessador(faker.lorem().word());
        serverAttribute.setSistema_operacional(faker.lorem().word());
        serverAttribute.setChipset(faker.lorem().word());
        serverAttribute.setMemoria(faker.lorem().word());
        serverAttribute.setSlots(faker.lorem().word());
        serverAttribute.setArmazenamento(faker.lorem().word());
        serverAttribute.setRede(faker.lorem().word());
        serverAttribute.setProduct(null); // You might want to adjust this based on your application logic
        return serverAttribute;
    }

    @BeforeEach
    public void setUp() {
        faker = new Faker();
        serverAttribute = criaAtributosServidor();
        entityManager.persistAndFlush(serverAttribute);
    }

    @Test
    public void testSave() {
        // Given
        ServerAttribute newServerAttribute = criaAtributosServidor();

        //When
        ServerAttribute savedServerAttribute = serverAttributeRepository.save(newServerAttribute);

        // Then
        assertThat(savedServerAttribute).isNotNull();
        assertThat(savedServerAttribute.getId()).isGreaterThan(0);


        
    }

    @Test
    public void testFindById() {
        // Given
        ServerAttribute savedServerAttribute = serverAttributeRepository.save(serverAttribute);

        // When
        Optional<ServerAttribute> retrievedAtributosServidor = serverAttributeRepository.findById(savedServerAttribute.getId());

        // Then
        assertEquals(serverAttribute, retrievedAtributosServidor.get());
        assertTrue(retrievedAtributosServidor.isPresent());
    }

   

    

    
}