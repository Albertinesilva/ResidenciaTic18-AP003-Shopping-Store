package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techie.shoppingstore.AP003.model.Carrinho;

public interface CategoriaRepository extends JpaRepository <Carrinho, Long> {

}
