// package br.com.techie.shoppingstore.AP003.repository;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// import com.github.javafaker.Faker;

// import br.com.techie.shoppingstore.AP003.model.Product;

// @DataJpaTest
// public class ProductRepositoryTest {

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private TestEntityManager entityManager;

//     private Product product;

//     public Faker faker;

//     private Product criaProduct() {

//         faker = new Faker();
//         Product product = new Product();
//         product.setAtributosServidor(null);
//         product.setCategory(null);
//         product.setDescricao(null);
//         product.setItemCarrinho(null);
//         product.setNome(faker.funnyName().name());
//         product.setPreco(faker.number().randomNumber());
//         product.setQtd_estoque(faker.number().randomDigit());
//         product.setUrl_imagem(faker.internet().image());
//         return product;
//     }

//     @BeforeEach
//     public void setUp() {
//         faker = new Faker();
//         product = criaProduct();
//         entityManager.persistAndFlush(product);
//     }

//     @Test
//     public void testSave() {

//         // Given
//         Product newProduct = criaProduct();

//         // When
//         Product savedProduct = productRepository.save(newProduct);

//         // Then
//         assertThat(savedProduct).isNotNull();
//         assertThat(savedProduct.getId()).isGreaterThan(0);

//         System.out.println(newProduct);

//     }

//     @Test
//     public void testFindById() {
//         // Given
//         Product savedProduct = productRepository.save(product);

//         // When
//         Optional<Product> retrievedProduct = productRepository.findById(savedProduct.getId());

//         // Then
//         assertEquals(product, retrievedProduct.get());
//         assertTrue(retrievedProduct.isPresent());

//         System.out.println(retrievedProduct);

//     }

// }
