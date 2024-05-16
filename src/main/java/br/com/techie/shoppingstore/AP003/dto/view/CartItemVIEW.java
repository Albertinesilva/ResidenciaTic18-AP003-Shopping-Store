package br.com.techie.shoppingstore.AP003.dto.view;

import java.math.BigDecimal;

public record CartItemVIEW(
        Long id,
        Long product_id,
        String product,
        BigDecimal price,
        Integer quantity
) {
}
