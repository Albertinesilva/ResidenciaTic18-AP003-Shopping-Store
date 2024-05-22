package br.com.techie.shoppingstore.AP003.dto.view;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record CartVIEW(
        Long id,
        Set<CartItemVIEW> cartItems,
        PaymentVIEW payment,
        UserSystemVIEW user,
        BigDecimal total_price,
        Integer total_items,
        LocalDateTime purchase_date,
        PaymentStatusEnum status
) {
}