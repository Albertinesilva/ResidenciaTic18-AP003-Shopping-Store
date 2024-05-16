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

import br.com.techie.shoppingstore.AP003.model.Produto;


@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Produto produto;

    public Faker faker;

    private Produto criaProduto() {

        faker = new Faker();
        Produto produto = new Produto();
        produto.setAtributosServidor(null);
        produto.setCategoria(null);
        produto.setDescricao(null);
        produto.setItemCarrinho(null);
        produto.setNome(faker.funnyName().name());
        produto.setPreco(faker.number().randomNumber());
        produto.setQtd_estoque(faker.number().randomDigit());
        produto.setUrl_imagem(faker.internet().image());
        return produto;
    }



    @BeforeEach
    public void setUp() {
        faker = new Faker();
        produto = criaProduto();
        entityManager.persistAndFlush(produto);
    }


    @Test
    public void testSave(){

        //Given
        Produto newProduto = criaProduto();

        //When
        Produto savedProduto = produtoRepository.save(newProduto);

        //Then
        assertThat(savedProduto).isNotNull();
        assertThat(savedProduto.getId()).isGreaterThan(0);

        System.out.println(newProduto);

    }

    @Test
    public void testFindById(){
        //  Given
        Produto savedProduto = produtoRepository.save(produto);

        //  When
        Optional<Produto> retrievedProduto = produtoRepository.findById(savedProduto.getId());

        // Then
        assertEquals(produto, retrievedProduto.get());
        assertTrue(retrievedProduto.isPresent());

        System.out.println(retrievedProduto);

    }


}
