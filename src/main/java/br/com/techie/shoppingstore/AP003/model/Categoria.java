package br.com.techie.shoppingstore.AP003.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // TODO Remover relação com produto
    @OneToMany(mappedBy = "categoria")
    private Set<Produto> produtos;

    // TODO Remover construtor
    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}

