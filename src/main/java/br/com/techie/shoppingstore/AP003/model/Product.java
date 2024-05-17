package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long preco;

    private String descricao;

    private int qtd_estoque;

    private String url_imagem;

    @OneToOne(mappedBy = "product")
    private AtributosServidor atributosServidor;

    @OneToOne(mappedBy = "product")
    private ItemCarrinho itemCarrinho;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
