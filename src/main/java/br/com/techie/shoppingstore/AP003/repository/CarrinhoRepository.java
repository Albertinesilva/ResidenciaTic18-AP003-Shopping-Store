package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.techie.shoppingstore.AP003.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository <Carrinho, Long> {

}
