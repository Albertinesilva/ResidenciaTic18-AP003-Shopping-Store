// package br.com.techie.shoppingstore.AP003.repository;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// import com.github.javafaker.Faker;

// import br.com.techie.shoppingstore.AP003.model.Carrinho;


// @DataJpaTest
// public class CarrinhoRepositoryTest {

//     @Autowired
//     private CarrinhoRepository carrinhoRepository;

//     @Autowired
//     private TestEntityManager entityManager;

//     private Carrinho carrinho;

//     public Faker faker;

//     private Carrinho criaCarrinho() {

//         faker = new Faker();
//         Carrinho carrinho = new Carrinho();
//         carrinho.setItem_carrinho_id(null);
//         carrinho.setPagamento(null);
//         carrinho.setUserSystem(null);
//         carrinho.setPreco_total(faker.number().randomNumber());
//         carrinho.setQtd_itens(faker.number().randomDigit());
//         carrinho.setDt_pedido(null);
//         carrinho.setStatus(faker.bool().bool());
        
//         return carrinho;
//     }


//     @BeforeEach
//     public void setUp() {
//         faker = new Faker();
//         carrinho = criaCarrinho();
//         entityManager.persistAndFlush(carrinho);
//     }


//     @Test
//     public void testSave(){
//         //Given
//         Carrinho newCarrinho = criaCarrinho();

//         //When
//         Carrinho savedCarrinho = carrinhoRepository.save(newCarrinho);

//         //Then
//         assertThat(savedCarrinho).isNotNull();
//         assertThat(savedCarrinho.getId()).isGreaterThan(0);

//     }

//     @Test
//     public void testFindById(){
//         //  Given
//         Carrinho savedCarrinho = carrinhoRepository.save(carrinho);

//         //  When
//         Optional<Carrinho> retrievedCarrinho = carrinhoRepository.findById(savedCarrinho.getId());

//         // Then
//         assertEquals(carrinho, retrievedCarrinho.get());
//         assertTrue(retrievedCarrinho.isPresent());

//     }



// }
