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

    // TODO Mudar para BigDecimal
    private Long preco;

    private String descricao;

    private int qtd_estoque;

    private String url_imagem;

    @OneToOne(mappedBy = "produto")
    private AtributosServidor atributosServidor;

    // TODO Remover essa relacao com o item carrinho
    @OneToOne(mappedBy = "produto")
    private ItemCarrinho itemCarrinho;

    // TODO Adicionar categoria do produto no service
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    


}
