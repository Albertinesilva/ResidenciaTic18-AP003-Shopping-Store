package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.CascadeType;
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
@Entity(name = "item_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private int qtd;

    // TODO Mudar para BigDecimal
    private Long preco;

    @OneToOne (cascade  = CascadeType.ALL)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    // TODO Alterar o nome da coluna para carrinho_id
    @ManyToOne
    @JoinColumn(name = "item_carrinho_id")
    private Carrinho carrinho;


}
