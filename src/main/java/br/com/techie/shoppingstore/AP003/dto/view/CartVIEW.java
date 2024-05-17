package br.com.techie.shoppingstore.AP003.dto.view;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CartVIEW(
        Long id,
        List<CartItemVIEW> cartItems,
        PaymentVIEW payment,
        UserVIEW user,
        BigDecimal total_price,
        Integer total_items,
        LocalDateTime purchase_date,
        PaymentStatusEnum status
) {
}
