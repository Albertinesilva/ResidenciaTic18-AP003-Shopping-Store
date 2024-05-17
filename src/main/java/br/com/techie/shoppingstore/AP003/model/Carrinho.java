package br.com.techie.shoppingstore.AP003.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    // TODO Remover essa relação com item carrinho
    @OneToMany(mappedBy = "carrinho")
    private Set<ItemCarrinho> item_carrinho_id;

    @OneToOne (cascade  = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;

    @OneToOne (cascade  = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    // TODO Mudar para BigDecimal
    private Long preco_total;

    private int qtd_itens;

    private LocalDateTime dt_pedido;

    // TODO Alterar para Enum de status do pagamento
    private boolean status;


}
