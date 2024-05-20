// package br.com.techie.shoppingstore.AP003.repository;

// import static org.assertj.core.api.Assertions.assertThat;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// import com.github.javafaker.Faker;

// import br.com.techie.shoppingstore.AP003.model.Category;

// @DataJpaTest
// public class CategoryRepositoryTest {

//     @Autowired
//     private CategoryRepository categoryRepository;

//     @Autowired
//     private TestEntityManager entityManager;

//     private Category category;

//     public Faker faker;

//     private Category criaCategory() {

//         faker = new Faker();
//         Category category = new Category();
//         category.setNome(faker.commerce().material());
//         category.setProducts(null);

//         return category;
//     }

//     @BeforeEach
//     public void setUp() {
//         faker = new Faker();
//         category = criaCategory();
//         entityManager.persistAndFlush(category);
//     }

//     @Test
//     public void testSave() {
//         // Given
//         Category newCategory = criaCategory();

//         // When
//         Category savedCategory = categoryRepository.save(newCategory);

//         // Then
//         assertThat(savedCategory).isNotNull();
//         assertThat(savedCategory.getId()).isGreaterThan(0);

//     }

//     @Test
//     public void testFindById() {
//         // Given
//         Category savedCategory = categoryRepository.save(category);

//         // When
//         Optional<Category> retrievedCategory = categoryRepository.findById(savedCategory.getId());

//         // Then
//         assertEquals(category, retrievedCategory.get());
//         assertTrue(retrievedCategory.isPresent());

//     }

// }
