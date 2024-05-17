package br.com.techie.shoppingstore.AP003.model;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice;

    private int totalItems;

    private LocalDateTime purchaseDate;

    private PaymentStatusEnum status;

    @OneToOne (cascade  = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @OneToOne (cascade  = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "Cart", cascade = CascadeType.ALL)
    private Set<CartItem> items;
}
