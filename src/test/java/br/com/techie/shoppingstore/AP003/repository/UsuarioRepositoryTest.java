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
public class UsuarioRepositoryTest {

    @Autowired
    private UserSystemRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    private UserSystem usuario;

    public Faker faker;

    // private UserSystem criaUsuario() {
        
    //     faker = new Faker();
    //     UserSystem usuario = new UserSystem();
    //     usuario.setAtivo(faker.bool().bool());
    //     usuario.setCarrinho(null);
    //     usuario.setSenha("@Test123");
    //     usuario.setConfirmacao_senha("@Test123");
    //     usuario.setEmail(faker.internet().emailAddress());
    //     usuario.setUsername(faker.name().username());
    //     return usuario;
    // }



    // @BeforeEach
    // public void setUp() {
    //     faker = new Faker();
    //     usuario = criaUsuario();
    //     entityManager.persistAndFlush(usuario);
    // }


    // @Test
    // public void testSave(){

    //     //Given
    //     UserSystem newUsuario = criaUsuario();

    //     //When
    //     UserSystem savedUsuario = usuarioRepository.save(newUsuario);

    //     //Then
    //     assertThat(savedUsuario).isNotNull();
    //     assertThat(savedUsuario.getId()).isGreaterThan(0);

    //     System.out.println(newUsuario);

    // }

    // @Test
    // public void testFindById(){
    //     //  Given
    //     UserSystem savedUsuario = usuarioRepository.save(usuario);

    //     //  When
    //     Optional<UserSystem> retrievedUsuario = usuarioRepository.findById(savedUsuario.getId());

    //     // Then
    //     assertEquals(usuario, retrievedUsuario.get());
    //     assertTrue(retrievedUsuario.isPresent());

    //     System.out.println(retrievedUsuario);

    // }


}
