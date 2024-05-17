package br.com.techie.shoppingstore.AP003.repository;

import br.com.techie.shoppingstore.AP003.model.UserSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {

  Optional<UserSystem> findByUsername(String username);

  @Query("select u.role from UserSystem u where u.username like :username")
  UserSystem.Role findRoleByUsername(String username);
}
