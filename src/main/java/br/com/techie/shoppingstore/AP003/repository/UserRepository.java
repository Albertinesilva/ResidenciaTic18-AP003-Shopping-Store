package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.techie.shoppingstore.AP003.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);

    @Query("select u.role from Users u where u.username like :username")
    RoleEnum findRoleByUsername(String username);
}
