package br.com.techie.shoppingstore.AP003.repository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.github.javafaker.Faker;

import br.com.techie.shoppingstore.AP003.model.Categoria;


@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Categoria categoria;

    public Faker faker;

    private Categoria criaCategoria() {

        faker = new Faker();
        Categoria categoria = new Categoria();
        categoria.setNome(faker.commerce().material());
        categoria.setProdutos(null);
        
        return categoria;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        categoria = criaCategoria();
        entityManager.persistAndFlush(categoria);
    }


    @Test
    public void testSave(){
        //Given
        Categoria newCategoria = criaCategoria();

        //When
        Categoria savedCategoria = categoriaRepository.save(newCategoria);

        //Then
        assertThat(savedCategoria).isNotNull();
        assertThat(savedCategoria.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindById(){
        //  Given
        Categoria savedCategoria = categoriaRepository.save(categoria);

        //  When
        Optional<Categoria> retrievedCategoria = categoriaRepository.findById(savedCategoria.getId());

        // Then
        assertEquals(categoria, retrievedCategoria.get());
        assertTrue(retrievedCategoria.isPresent());

    }



}


