package br.com.techie.shoppingstore.AP003.dto.view;

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
        // FIXME Alterar para Enum de Status do pagamento
        //  - OPEN_PAYMENT  (PAGAMENTO EM ABERTO)
        //  - PAYMENT_MADE  (PAGAMENTO REALIZADO)
        //  - OVERDUE_PAYMENT (PAGAMENTO EM ATRASO)
        String status
) {
}
