package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.techie.shoppingstore.AP003.model.CartItem;

import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository <CartItem, Long> {

    Set<CartItem> findAllByCartId(Long id);
}
