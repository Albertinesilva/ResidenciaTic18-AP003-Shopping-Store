package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String senha;

    private String confirmacao_senha;

    private boolean ativo;

    @OneToOne(mappedBy = "usuario")
    private Carrinho carrinho;

}
