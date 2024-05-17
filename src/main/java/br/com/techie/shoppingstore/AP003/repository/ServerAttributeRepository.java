package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.techie.shoppingstore.AP003.model.ServerAttribute;

import java.util.Optional;

@Repository
public interface ServerAttributeRepository extends JpaRepository <ServerAttribute, Long> {
    Optional<ServerAttribute> findByProductId(Long productId);
}
