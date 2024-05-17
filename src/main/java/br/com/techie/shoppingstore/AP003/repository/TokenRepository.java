package br.com.techie.shoppingstore.AP003.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.techie.shoppingstore.AP003.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> findByToken(String token);

}
