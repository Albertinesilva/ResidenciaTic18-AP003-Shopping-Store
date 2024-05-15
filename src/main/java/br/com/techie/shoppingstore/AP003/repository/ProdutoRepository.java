package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techie.shoppingstore.AP003.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

}
