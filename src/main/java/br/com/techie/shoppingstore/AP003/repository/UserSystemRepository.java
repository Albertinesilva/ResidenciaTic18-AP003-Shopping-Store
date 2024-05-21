package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.techie.shoppingstore.AP003.model.UserSystem;

import java.util.Optional;

@Repository
public interface UserSystemRepository extends JpaRepository <UserSystem, Long> {
    Optional<UserSystem> findByEmail(String email);

    @Query("select u.role from Users u where u.email like :email")
    RoleEnum findRoleByEmail(String email);
}
