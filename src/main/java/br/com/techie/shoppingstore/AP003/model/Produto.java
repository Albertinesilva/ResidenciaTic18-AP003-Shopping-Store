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
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Long preco;

    private String descricao;

    private int qtd_estoque;

    private String url_imagem;

    @OneToOne(mappedBy = "produto")
    private AtributosServidor atributosServidor;

    @OneToOne(mappedBy = "produto")
    private ItemCarrinho itemCarrinho;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    


}
