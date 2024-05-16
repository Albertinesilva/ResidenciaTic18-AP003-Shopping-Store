package br.com.techie.shoppingstore.AP003.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techie.shoppingstore.AP003.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
