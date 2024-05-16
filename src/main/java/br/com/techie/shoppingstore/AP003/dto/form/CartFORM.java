package br.com.techie.shoppingstore.AP003.dto.form;

import br.com.techie.shoppingstore.AP003.dto.view.CartItemVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserVIEW;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record CartFORM(
        Long user_id,
        BigDecimal total_price,
        Integer total_items,
        LocalDateTime purchase_date
) {
}
