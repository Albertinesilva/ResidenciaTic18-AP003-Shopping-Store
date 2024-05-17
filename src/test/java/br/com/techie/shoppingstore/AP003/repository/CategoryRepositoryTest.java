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

import br.com.techie.shoppingstore.AP003.model.Category;


@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Category category;

    public Faker faker;

    private Category criaCategoria() {

        faker = new Faker();
        Category category = new Category();
        category.setNome(faker.commerce().material());
        category.setProdutos(null);
        
        return category;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        category = criaCategoria();
        entityManager.persistAndFlush(category);
    }


    @Test
    public void testSave(){
        //Given
        Category newCategory = criaCategoria();

        //When
        Category savedCategory = categoryRepository.save(newCategory);

        //Then
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindById(){
        //  Given
        Category savedCategory = categoryRepository.save(category);

        //  When
        Optional<Category> retrievedCategoria = categoryRepository.findById(savedCategory.getId());

        // Then
        assertEquals(category, retrievedCategoria.get());
        assertTrue(retrievedCategoria.isPresent());

    }



}


