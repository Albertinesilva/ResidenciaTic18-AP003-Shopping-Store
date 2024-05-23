package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<<< HEAD:src/main/java/br/com/techie/shoppingstore/AP003/repository/PaymentRepository.java
import br.com.techie.shoppingstore.AP003.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
========
import br.com.techie.shoppingstore.AP003.model.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
>>>>>>>> feature/security:src/main/java/br/com/techie/shoppingstore/AP003/repository/ProductRepository.java

}
